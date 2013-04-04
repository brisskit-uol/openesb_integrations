#!/bin/bash
#-----------------------------------------------------------------------------------------------
# Installs the caCatissueESB and all its prerequisites:
#
# Mandatory: the OPENESB_INSTALL_PROCS_HOME environment variable to be set.
# Optional : the OPENESB_INSTALL_WORKSPACE environment variable.
# The latter is an optional full path to a workspace area. If not set, defaults to a workspace
# within the install home.
#
# USAGE: {script-file-name}.sh job-name
# Where: 
#   job-name is a suitable tag to group all jobs associated with the overall workflow
# Notes:
#   The job-name is used to create a work directory for the overall workflow; eg:
#   OPENESB_INSTALL_PROCS_HOME/job-name
#   This work directory must already exist.
#
# Author: Jeff Lusted (jl99@leicester.ac.uk)
#-----------------------------------------------------------------------------------------------
source $OPENESB_INSTALL_PROCS_HOME/bin/common/setenv.sh
source $OPENESB_INSTALL_PROCS_HOME/bin/common/functions.sh

#=======================================================================
# First, some basic checks...
#=======================================================================
#
# Check on the usage...
if [ ! $# -eq 1 ]
then
	echo "Error! Incorrect number of arguments."
	echo ""
	print_usage
	exit 1
fi

#
# Retrieve job-name into its variable...
JOB_NAME=$1

$OPENESB_INSTALL_PROCS_HOME/bin/installs/1-acquisitions.sh $JOB_NAME
$OPENESB_INSTALL_PROCS_HOME/bin/installs/3-install-jdk.sh $JOB_NAME
$OPENESB_INSTALL_PROCS_HOME/bin/installs/4-install-glassfish.sh $JOB_NAME
$OPENESB_INSTALL_PROCS_HOME/bin/installs/5-install-catissueESB.sh $JOB_NAME
