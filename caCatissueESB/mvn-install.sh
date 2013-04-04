#!/bin/bash
#-----------------------------------------------------------------------------------------------
# This is a KLUDGE to get around the problems of building maven artifacts in OpenESB 
# You must edit this script to acquire the latest version of the catissue deploy jar.
#
# A better way of doing this will follow when we understand how to build OpenESB
# artifacts from the command line using ANT scripts.
#
# Author: Jeff Lusted (jl99@leicester.ac.uk)
#-----------------------------------------------------------------------------------------------
#==============================================================================================
# You must edit this script and place the maven url to the latest deploy jar artifact here...
#==============================================================================================
DEPLOY_JAR_ARTIFACT_URL=http://maven.brisskit.org/nexus/content/repositories/releases/org/brisskit/app/openesb/bpCatissueESB/1.0-RC1/bpCatissueESB-1.0-RC1.jar
#===========================================================================
# Cleanup the local build and download the artifact...
#===========================================================================
rm ./build/bpCatissueESB.jar >/dev/null 2>/dev/null
wget -O ./build/bpCatissueESB.jar ${DEPLOY_JAR_ARTIFACT_URL}

#===========================================================================
# Do the local maven install ...
#===========================================================================
mvn clean install 
echo ""
echo "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"
echo "===>Local development install complete."
echo "===>Please remember NOT to commit jar or zip artifacts to SVN."
echo "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"
