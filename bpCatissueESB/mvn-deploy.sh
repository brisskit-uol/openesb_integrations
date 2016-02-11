#!/bin/bash
#-----------------------------------------------------------------------------------------------
# This is a KLUDGE to get around the problems of building maven artifacts in OpenESB 
# You must edit this script to acquire the latest version of the catissue connector.
#
# A better way of doing this will follow when we understand how to build OpenESB
# artifacts from the command line using ANT scripts.
#
# Author: Jeff Lusted (jl99@leicester.ac.uk)
#-----------------------------------------------------------------------------------------------
#==============================================================================================
# You must edit this script and place the maven url to the latest connector artifact here...
#==============================================================================================
CONNECTOR_ARTIFACT_URL=http://maven.brisskit.le.ac.uk/nexus/content/repositories/releases/org/brisskit/app/openesb/catissueConnector/1.0-RC1/catissueConnector-1.0-RC1.jar
#===========================================================================
# Cleanup the local build and download the artifact...
#===========================================================================
rm ./build/META-INF/src/_references/_relative/__/catissueConnector.jar >/dev/null 2>/dev/null
wget --user=readonly --password=readonly..... -O ./build/META-INF/src/_references/_relative/__/catissueConnector.jar ${CONNECTOR_ARTIFACT_URL}

#===========================================================================
# Do the remote maven deploy ...
#===========================================================================
mvn clean deploy 
echo ""
echo "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"
echo "===>Remote deployment complete."
echo "===>Please remember NOT to commit jar or zip artifacts to SVN."
echo "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"
