#
# rdc-monitor-server Dockerfile
#
FROM java:8

# Author
MAINTAINER uname.CHEN <uname.chen@gmail.com>

#move the appliation files to image
ADD target/bin/ /opt/webapp/

# Environment prepare
EXPOSE 5003

#switch to the place
WORKDIR /opt/webapp/

#starting the app
CMD ["java", "-jar", "rdc-monitor-server.jar"]