#!/bin/sh
mvn clean
mvn install
echo "copy war..."
cp financial-web/target/financial-web.war $JBOSS_HOME/standalone/deployments/
echo "deploy success!"

#nohup sh standalone.sh > nohup.out 2>&1 &
sh $JBOSS_HOME/bin/standalone.sh

