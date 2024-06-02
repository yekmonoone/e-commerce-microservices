# Base image olarak openjdk:20 kullanılıyor
FROM openjdk:20-jdk

# Uygulama içindeki JAR dosyasını tanımla
ARG JAR_FILE=target/cartservice-0.0.1-SNAPSHOT.jar

# Çalışma dizinini oluştur
WORKDIR /app

# JAR dosyasını çalışma dizinine kopyala
COPY ${JAR_FILE} app.jar

# Uygulamanın çalıştırılma komutu
ENTRYPOINT ["java", "-jar", "app.jar"]
