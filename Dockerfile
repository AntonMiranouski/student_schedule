FROM openjdk:8
ADD target/student_schedule.jar student_schedule.jar
ENTRYPOINT ["java", "-jar","student_schedule.jar"]
EXPOSE 8080