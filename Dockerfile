FROM azul/zulu-openjdk-alpine:11
WORKDIR /
ADD /build/libs /jar/
EXPOSE 4545
Cmd java -cp /jar/ticketsystem-service-all.jar ticketsystem.service.Publisher