# Make jdmc package


DIRS =  ../../jdmc
VERSION=`tail -n1 ../Version`
STAGE_DIR=../_work
TRUE = /bin/true

all: prepare $(DIRS) files release

$(DIRS)::
	@echo "staging `basename $@` ..."

clean:
	rm -f Release/*.deb

prepare: 
	rm -rf $(STAGE_DIR)
	mkdir -p $(STAGE_DIR)
	mkdir -p $(STAGE_DIR)/debian
	cp debian/* $(STAGE_DIR)/debian

files:

release:
	(cd .. && ./version.sh)
	sed -i s~Version:.*~"Version: `tail -n1 ../Version`"~g debian/control
	(cd ${STAGE_DIR} && debuild)
