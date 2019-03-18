FROM openjdk:8-jre
MAINTAINER Alejandro Sánchez <a.sanchez3329@gmail.com>

ENTRYPOINT ["/usr/bin/java", "-jar", "/usr/share/oompa-manager/oompamanager.jar"]

ARG JAR_FILE
ADD target/${JAR_FILE} /usr/share/oompa-manager/oompamanager.jar