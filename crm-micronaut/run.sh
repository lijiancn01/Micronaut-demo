#!/bin/bash
cd /workspace/crm-micronaut

# Build classpath
CP="target/classes"
for JAR in $(find /root/.m2/repository -name "*.jar"); do
  CP="$CP:$JAR"
done

echo "Starting Micronaut application..."
java -cp "$CP" -XX:+DisableAttachMechanism com.crm.demo.Application
