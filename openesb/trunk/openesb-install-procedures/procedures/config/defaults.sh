#!/bin/bash
#
# Default settings used by scripts within the bin directory
# 
#-------------------------------------------------------------------

# Log file name:
export JOB_LOG_NAME=job.log

# Name of directory to hold archives of source, 
# demo data and others acquired from elsewhere
export ACQUISITIONS_DIRECTORY=acquisitions

# We need a user and password for wget to maven repo
export MVN_DEPLOY_USER=deployment
export MVN_DEPLOY_PASSWORD=deploy.....

# Download paths of installable artifacts... 
export JDK_DOWNLOAD_PATH=http://maven.brisskit.org/nexus/content/repositories/thirdparty/oracle/jdk/jdk/7u17-linux/jdk-7u17-linux-x64.tar.gz
export GLASSFISH_DOWNLOAD_PATH=http://maven.brisskit.org/nexus/content/repositories/thirdparty/openesb/Brisskit-GlassFishESB/v22/Brisskit-GlassFishESB-v22.zip
export BRISSKIT_CATISSUE_ESB_DOWNLOAD_PATH=http://maven.brisskit.org/nexus/content/repositories/releases/org/brisskit/app/openesb/caCatissueESB/1.0-RC1/caCatissueESB-1.0-RC1.zip
# JDK expanded directory name (and version)...
export JDK=jdk1.7.0_17

# Deployed caCatissueESB artifact zip name...
DEPLOYED_ARTIFACT=caCatissueESB.zip

#
DEPLOYED_GLASSFISH_DIRECTORY_NAME=GlassFishESBv22

# Custom space for the install workspace (if required)
# If not defined, defaults to OPENESB_INSTALL_PROCS_HOME/work
#export OPENESB_INSTALL_WORKSPACE=?

#---------------------------------------------------------------------------------
# Java home directory...
#---------------------------------------------------------------------------------
export JAVA_HOME=$ONYX_INSTALL_DIRECTORY/jdk
