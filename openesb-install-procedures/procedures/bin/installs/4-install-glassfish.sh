#!/bin/bash
#-----------------------------------------------------------------------------------------------
# Installs and glassfish server:
#
# Mandatory: the OPENESB_INSTALL_PROCS_HOME environment variable to be set.
# Optional : the OPENESB_INSTALL_WORKSPACE environment variable.
# The latter is an optional full path to a workspace area. If not set, defaults to a workspace
# within the install home.
#
# Pre-reqs:
#   1-acquisitions.sh has been run at some point to acquire the tomcat zip file
#
# USAGE: {script-file-name}.sh job-name
# Where: 
#   job-name is a suitable tag to group all jobs associated with the overall workflow
# Notes:
#   The job-name is used to create a work directory for the overall workflow; eg:
#   OPENESB_INSTALL_PROCS_HOME/job-name
#   This work directory must already exist.
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
WORK_DIR=$OPENESB_INSTALL_WORKSPACE/$JOB_NAME
LOG_FILE=$WORK_DIR/$JOB_LOG_NAME

#
# We must already have a work directory for this job step
# (otherwise no acquisitions)...
if [ ! -d $WORK_DIR ]
then
	echo "Error! Could not find work directory."
	echo "Please check acquisitions step has been run and that job name \"$JOB_NAME\" is correct."
	exit 1
fi

#===========================================================================
# Print a banner for this step of the job.
#===========================================================================
print_banner $0 $JOB_NAME $LOG_FILE 

#===========================================================================
# The real work is about to start.
# Give the user a warning...
#=========================================================================== 
echo "About to install Glassfish..."
echo ""
echo "   Please note detailed log messages are written to $LOG_FILE"
echo "   If you want to see this during execution, try: tail -f $LOG_FILE"
echo ""

GLASSFISH_INSTALL_DIRECTORY_NAME=$( basename $GLASSFISH_DOWNLOAD_PATH .zip )
#
# Unzip glassfish file...
print_message "" $LOG_FILE
print_message "Unzipping Glassfish..." $LOG_FILE
unzip -o $WORK_DIR/$ACQUISITIONS_DIRECTORY/$( basename $GLASSFISH_DOWNLOAD_PATH .zip ) -d $OPENESB_INSTALL_DIRECTORY >>$LOG_FILE 2>>$LOG_FILE
exit_if_bad $? "Failed to install Glassfish" $LOG_FILE

#
# Create a symbolic link to glassfish...
ln -s $OPENESB_INSTALL_DIRECTORY/$DEPLOYED_GLASSFISH_DIRECTORY_NAME $OPENESB_INSTALL_DIRECTORY/glassfish


#=========================================================================
# If we got this far, we must be successful...
#=========================================================================
print_message "Glassfish installation completed." $LOG_FILE
print_footer $0 $JOB_NAME $LOG_FILE