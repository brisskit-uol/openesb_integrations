#!/bin/bash
#-----------------------------------------------------------------------------------------------
# Acquires install artifacts.
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
#   The job-name is used to create a working directory for the overall workflow; eg:
#   OPENESB_INSTALL_PROCS_HOME/job-name
#   This working directory is created if it does not exist.
#
# Further tailoring can be achieved via the defaults.sh script.
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

#
# It is possible to set your own procedures workspace.
# But if it doesn't exist, we create one for you within the procedures home...
if [ -z $OPENESB_INSTALL_WORKSPACE ]
then
	OPENESB_INSTALL_WORKSPACE=$OPENESB_INSTALL_PROCS_HOME/work
fi

#
# Establish a log file for the job...
LOG_FILE=$OPENESB_INSTALL_WORKSPACE/$JOB_NAME/$JOB_LOG_NAME

#
# If required, create a working directory for this job step.
ACQUISITIONS=$OPENESB_INSTALL_WORKSPACE/$JOB_NAME/$ACQUISITIONS_DIRECTORY
if [ ! -d $ACQUISITIONS ]
then
	mkdir -p $ACQUISITIONS
	exit_if_bad $? "Failed to create working directory $ACQUISITIONS"
fi

#===========================================================================
# Print a banner for this step of the job.
#===========================================================================
print_banner $0 $JOB_NAME $LOG_FILE 

#===========================================================================
# The real work is about to start.
# Give the user a warning...
#=========================================================================== 
print_message "About to acquire prerequisite files for OpenESB install" $LOG_FILE

print_message "" $LOG_FILE
print_message "Acquiring Java JDK..." $LOG_FILE

wget --user=$MVN_READONLY_USER \
     --password=$MVN_READONLY_PASSWORD \
     -O $ACQUISITIONS/$( basename $JDK_DOWNLOAD_PATH ) $JDK_DOWNLOAD_PATH
exit_if_bad $? "Failed to acquire Java JDK." $LOG_FILE
print_message "Success! Acquired Java JDK." $LOG_FILE

print_message "" $LOG_FILE
print_message "Acquiring Glassfish..." $LOG_FILE
wget --user=$MVN_READONLY_USER \
     --password=$MVN_READONLY_PASSWORD \
     -O $ACQUISITIONS/$( basename $GLASSFISH_DOWNLOAD_PATH ) $GLASSFISH_DOWNLOAD_PATH
exit_if_bad $? "Failed to acquire Glassfish." $LOG_FILE
print_message "Success! Acquired Glassfish." $LOG_FILE

print_message "" $LOG_FILE
print_message "Acquiring Catissue ESB..." $LOG_FILE
print_message "DEBUG: $ACQUISITIONS/$DEPLOYED_ARTIFACT" $LOG_FILE
print_message "DEBUG: $BRISSKIT_CATISSUE_ESB_DOWNLOAD_PATH" $LOG_FILE
wget --user=$MVN_READONLY_USER \
     --password=$MVN_READONLY_PASSWORD \
     -O $ACQUISITIONS/$DEPLOYED_ARTIFACT $BRISSKIT_CATISSUE_ESB_DOWNLOAD_PATH
exit_if_bad $? "Failed to acquire Catissue ESB." $LOG_FILE
print_message "Success! Acquired Catissue ESB." $LOG_FILE

#=========================================================================
# If we got this far, we must be successful...
#=========================================================================
print_footer $0 $JOB_NAME $LOG_FILE