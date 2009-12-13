#
# Regular cron jobs for the jdmc package
#
0 4	* * *	root	[ -x /usr/bin/jdmc_maintenance ] && /usr/bin/jdmc_maintenance
