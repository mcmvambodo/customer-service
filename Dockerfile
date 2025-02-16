FROM maven:3.8.7-openjdk-18 as build
WORKDIR /build
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package spring-boot:repackage -DskipTests


FROM amazoncorretto:21
ARG PROFILE=dev
ARG VERSION=1.0.0
WORKDIR /app
COPY --from=build /build/target/customerservice-*.jar /app/
EXPOSE 8082
ENV ACTIVE_PROFILE=${PROFILE}
ENV APP_VERSION=${VERSION}
CMD java -jar -Dspring.profiles.active=${ACTIVE_PROFILE} customerservice-${APP_VERSION}.jar