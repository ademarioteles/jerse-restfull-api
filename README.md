# API RESTFULL JERSEY FRAMEWORK

Api desenvolvida na linguagem de programação java e com auxílio do Jersey, Jpa, Apache Tomcat e Hibernate. Essa api possui os seguintes endpoints:

1- Adress

Acesso: http://localhost:8080/api/adress

2- Category

Acesso: http://localhost:8080/api/category

3- Product

Acesso: http://localhost:8080/api/product

4- Orders

Acesso: http://localhost:8080/api/orders

E essas entidades estão organizadas da seguinte forma:

![Database](/images/database.png)

### 📝 Jersey
```
        <dependency>
			<groupId>org.glassfish.jersey.containers</groupId>
			<artifactId>jersey-container-servlet-core</artifactId>
		</dependency>
		<dependency>
			<groupId>org.glassfish.jersey.media</groupId>
			<artifactId>jersey-media-json-jackson</artifactId>
		</dependency>
		<dependency>
			<groupId>org.glassfish.jersey.inject</groupId>
			<artifactId>jersey-hk2</artifactId>
		</dependency>
		<dependency>
			<groupId>org.glassfish.jersey.media</groupId>
			<artifactId>jersey-media-json-binding</artifactId>
		</dependency>
```
### 📝 Hibernate

```
		<dependency>
			<groupId>org.hibernate</groupId>
			<artifactId>hibernate-core</artifactId>
			<version>5.4.12.Final</version>
		</dependency>
		<!-- https://mvnrepository.com/artifact/org.hibernate/hibernate-entitymanager -->
		<dependency>
			<groupId>org.hibernate</groupId>
			<artifactId>hibernate-entitymanager</artifactId>
			<version>5.4.12.Final</version>
		</dependency>
```

### ☕ Java

```
	    <configuration>
		    <sour   1.8</source>
		    <target>1.8</target>
	    </configuration>
```