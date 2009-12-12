#!/bin/bash
git rev-list HEAD | sort > config.git-hash
LOCALVER=`wc -l config.git-hash | awk '{print $1}'`
if [ $LOCALVER \> 1 ] ; then
    VER=`git rev-list origin/master | sort | join config.git-hash - | wc -l | awk '{print $1}'`
    if [ $VER != $LOCALVER ] ; then
        VER="$VER+$(($LOCALVER-$VER))"
    elif git status | grep -q "modified:" ; then
        VER="${VER}M"
    fi
    VER="$VER $(git rev-list HEAD -n 1 | head -c 7)"
else
    echo "#define X264_VERSION \"\"" >> config.h
    VER="x"
fi
rm -f config.git-hash
API=`sed -e 's/.* \([1-9][0-9]*\).*/\1/' BUILD`
echo "0.$API.$VER" >> Version
