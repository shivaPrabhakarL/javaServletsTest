FROM ubuntu:latest
RUN apt-get update
RUN apt-get upgrade 
RUN apt-get install -y git
RUN git clone https://github.com/shivaPrabhakarL/javaServletsTest.git
RUN apt-get install -y sudo
RUN apt-get -y install openjdk-8-jdk wget

RUN wget https://archive.apache.org/dist/tomcat/tomcat-8/v8.5.34/bin/apache-tomcat-8.5.34.tar.gz
RUN tar xvfz apache-tomcat-8.5.34.tar.gz
RUN apt-get install -y mysql-server

ADD To-Do.war /apache-tomcat-8.5.34/webapps/
EXPOSE 8080
CMD /apache-tomcat-8.5.34/bin/catalina.sh run

