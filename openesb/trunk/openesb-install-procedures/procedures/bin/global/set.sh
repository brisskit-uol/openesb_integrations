#!/bin/bash
#-----------------------------------------------------------------------------------------------------------
# Source this at the start of any shell session.
# (There should be no need to do this as sudo or root)
# Use "source ./set.sh" or ". ./set.sh" at the command line or within a composition script.
# Remember, if you execute any script as sudo, then you must inherit the environment variables; eg:
# > sudo -E ./0-prerequisites.sh job-20130214
#
# NOTES.
# (1) Edit setting for OPENESB_INSTALL_DIRECTORY.
# (2) Edit setting for local INSTALL_PACKAGE_NAME in order to pick up the correct version of the procedures.
#-----------------------------------------------------------------------------------------------------------
export OPENESB_INSTALL_DIRECTORY=/var/local/brisskit/openesb
INSTALL_PACKAGE_NAME=openesb-v22-install-procedures-1.0-RC1
export OPENESB_INSTALL_PROCS_HOME=$OPENESB_INSTALL_DIRECTORY/${INSTALL_PACKAGE_NAME}
