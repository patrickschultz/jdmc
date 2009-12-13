# Make igcore packages

include ../Defines

TRUE = /bin/true

DIRS = \
	   $(wildcard jdmc) \
	   $(wildcard jdmc-src)

all : $(DIRS)

$(DIRS)::
	@cd $@; test -f Makefile && $(MAKE); /bin/true

stage ::
	@./stage


distclean clean:
	rm -f Release/*.deb
	rm -f Release/packages.lst
