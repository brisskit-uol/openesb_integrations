#!/bin/bash
#
# Basic environment variables for OpenESB
#
#-------------------------------------------------------------------
if [ -z $OPENESB_INSTALL_DEFAULTS_DEFINED ]
then
	export OPENESB_INSTALL_DEFAULTS_DEFINED=true	
	source $OPENESB_INSTALL_PROCS_HOME/config/defaults.sh	
fi


