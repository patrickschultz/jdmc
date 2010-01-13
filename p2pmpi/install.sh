#!/bin/bash
#
#  Copyright (C) 2009 Patrick Schultz <schultz.patrick@gmail.com>
#
#  This program is free software: you can redistribute it and/or modify
#  it under the terms of the GNU General Public License as published by
#  the Free Software Foundation, either version 3 of the License, or
#  (at your option) any later version.
#
#  This program is distributed in the hope that it will be useful,
#  but WITHOUT ANY WARRANTY; without even the implied warranty of
#  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
#  GNU General Public License for more details.
#
#  You should have received a copy of the GNU General Public License
#  along with this program.  If not, see <http://www.gnu.org/licenses/>.
#

##
# @file install.sh
#
# The following script provides setup and configuration options that are unique
# to each user. It shall prompt the user for their imput to allow for jdmc and
# supporting tools to be configured. It can be run at any time to setup or change
# configuration values. It should be ovbious, but this script is for for configuring
# JDMC on Linux.
#

set -e

##################################################################
# Global Variables
##################################################################
SUPERNODEVAL=invalid
SUPERNODE_PORT=9700


##################################################################
# Functions
##################################################################

##
# Sets variable IP to the first ip found in ifconfig
#
get_ip()
{
	OS=`uname`
	case $OS in
	   	Linux) IP=`ifconfig  | grep 'inet addr:'| grep -v '127.0.0.1' | cut -d: -f2 | awk '{ print $1}'`;;
	   	FreeBSD|OpenBSD) IP=`ifconfig  | grep -E 'inet.[0-9]' | grep -v '127.0.0.1' | awk '{ print $2}'` ;;
	   	SunOS) IP=`ifconfig -a | grep inet | grep -v '127.0.0.1' | awk '{ print $2} '` ;;
		*) IP="Unknown";;
	esac
}

# Test an IP address for validity:
# Usage:
#      valid_ip IP_ADDRESS
#      if [[ $? -eq 0 ]]; then echo good; else echo bad; fi
#   OR
#      if valid_ip IP_ADDRESS; then echo good; else echo bad; fi
#
function valid_ip()
{
    local  ip=$1
    local  stat=1

    if [[ $ip =~ ^[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}$ ]]; then
        OIFS=$IFS
        IFS='.'
        ip=($ip)
        IFS=$OIFS
        [[ ${ip[0]} -le 255 && ${ip[1]} -le 255 \
            && ${ip[2]} -le 255 && ${ip[3]} -le 255 ]]
        stat=$?
    fi
    return $stat
}

##################################################################
# Main
##################################################################

echo -n "Would you like to configure JDMC now? [y]/n "
read -n 1 response
echo
[ ! $response == "y" ] && [ ! $response == "Y" ] && exit 0

# Guess the ip of the user
if [ ! -n "${IP+x}" ]; then
	get_ip
fi

echo "Please input ip address or host name of the super node. "
echo -n "If this host is the supernode, then use its ip [$IP]: "

while ! valid_ip $SUPERNODEVAL; do
	read response
	if [ "$response" == "" ]; then
		SUPERNODEVAL=$IP
	elif valid_ip $SUPERNODEVAL; then
		SUPERNODEVAL=$response
	else
		echo -n "Invalid IP address. Please reenter [$IP]: "
	fi
done

echo "Please enter the supernode port number if "

echo -n "you wish to use a non-default value [$SUPERNODE_PORT]: "
read response

#Filter out anything but a number
set +e
response=$(echo $response | grep -o -E ^[0-9]*)
set -e

#Check that the user inputed a value
if [ ! "$response" == "" ]; then
    SUPERNODE_PORT=$response
fi

sed -i s~^SUPERNODE=.*~SUPERNODE=tcp://${SUPERNODEVAL}:${SUPERNODE_PORT}~g P2P-MPI.conf
