# Temel olarak OpenJDK image'ını kullan
FROM openjdk:17-jdk-slim

# Uygulama dosyası için bir çalışma dizini oluştur
WORKDIR /app

# Projeyi derleyip çalıştırabilmek için gerekli dosyaları kopyala
COPY target/paymentService-0.0.1-SNAPSHOT.jar /app/paymentService.jar

# Uygulamanın çalıştırılacağı komutu tanımla
ENTRYPOINT ["java", "-jar", "/app/paymentService.jar"]

# Uygulamanın çalışacağı portu belirt
EXPOSE 8086
