# rest-bug-tracker
Система агрегации и анализа информации об ошибках на основе REST интерфейса.

## Технологии

* PostgreSQL 9.5.4
* Spring 4.3.2
* Hibernate 4.3.11
* Jersey 2.24
* JDK 8
* Maven 3
* Glassfish 4.1

# API

## Получить список ошибок

``` json
GET http://localhost:8080/rest-error-tracker/api/errors/
```
``` json
[
  {
    "fixed": false,
    "id": 8,
    "registrationTimestamp": "2016-11-23T13:12:24.665+03:00",
    "source": "Test project",
    "stackTrace": "Exception in thread \"main\" java.lang.ArithmeticException: / by zero\nat Test.main(Test.java:4)",
    "title": "Divide by zero"
  },
  {
    "fixed": false,
    "id": 9,
    "registrationTimestamp": "2016-11-23T13:14:24.36+03:00",
    "source": "Other test project",
    "stackTrace": "Exception in thread \"main\" java.lang.ArithmeticException: / by zero\nat Test.main(Test.java:4)",
    "title": "Divide by zero"
  },
  {
    "fixed": false,
    "id": 10,
    "registrationTimestamp": "2016-11-23T13:16:42.826+03:00",
    "source": "Test project",
    "stackTrace": "Exception in thread \"main\" java.lang.NullPointerException\nat Test.main(Test.java:4)",
    "title": "Null pointer exception for string"
  },
  {
    "fixed": false,
    "id": 11,
    "registrationTimestamp": "2016-11-23T13:17:22.117+03:00",
    "source": "Test project",
    "stackTrace": "Exception in thread \"main\" java.lang.NullPointerException\nat Test.main(Test.java:4)",
    "title": "Null pointer exception for string 2"
  }
]
```


## Получить информацию об ошибке по id

``` json
GET http://localhost:8080/rest-error-tracker/api/errors/9/
```

``` json
{
  "fixed": false,
  "id": 9,
  "registrationTimestamp": "2016-11-23T13:14:24.36+03:00",
  "source": "Other test project",
  "stackTrace": "Exception in thread \"main\" java.lang.ArithmeticException: / by zero\nat Test.main(Test.java:4)",
  "title": "Divide by zero"
}
```

## Получить список ошибок без дубликатов

``` json
GET http://localhost:8080/rest-error-tracker/api/errors/without-duplicates/
```

``` json
[
  {
    "fixed": false,
    "id": 8,
    "registrationTimestamp": "2016-11-23T13:12:24.665+03:00",
    "source": "Test project",
    "stackTrace": "Exception in thread \"main\" java.lang.ArithmeticException: / by zero\nat Test.main(Test.java:4)",
    "title": "Divide by zero"
  },
  {
    "fixed": false,
    "id": 9,
    "registrationTimestamp": "2016-11-23T13:14:24.36+03:00",
    "source": "Other test project",
    "stackTrace": "Exception in thread \"main\" java.lang.ArithmeticException: / by zero\nat Test.main(Test.java:4)",
    "title": "Divide by zero"
  },
  {
    "fixed": false,
    "id": 10,
    "registrationTimestamp": "2016-11-23T13:16:42.826+03:00",
    "source": "Test project",
    "stackTrace": "Exception in thread \"main\" java.lang.NullPointerException\nat Test.main(Test.java:4)",
    "title": "Null pointer exception for string"
  }
]
```



## Получить список ошибок, сгруппированных по дубликтам 

``` json
GET http://localhost:8080/rest-error-tracker/api/errors/group-by-duplicates/
```

``` json
[
  [
    {
      "id": 10,
      "title": "Null pointer exception for string",
      "source": "Test project",
      "stackTrace": "Exception in thread \"main\" java.lang.NullPointerException\nat Test.main(Test.java:4)",
      "registrationTimestamp": 1479896202826,
      "fixed": false
    },
    {
      "id": 11,
      "title": "Null pointer exception for string 2",
      "source": "Test project",
      "stackTrace": "Exception in thread \"main\" java.lang.NullPointerException\nat Test.main(Test.java:4)",
      "registrationTimestamp": 1479896242117,
      "fixed": false
    }
  ],
  [
    {
      "id": 8,
      "title": "Divide by zero",
      "source": "Test project",
      "stackTrace": "Exception in thread \"main\" java.lang.ArithmeticException: / by zero\nat Test.main(Test.java:4)",
      "registrationTimestamp": 1479895944665,
      "fixed": false
    }
  ],
  [
    {
      "id": 9,
      "title": "Divide by zero",
      "source": "Other test project",
      "stackTrace": "Exception in thread \"main\" java.lang.ArithmeticException: / by zero\nat Test.main(Test.java:4)",
      "registrationTimestamp": 1479896064360,
      "fixed": false
    }
  ]
]
```

## Получить спсиок активных ошибок

``` json
GET http://localhost:8080/rest-error-tracker/api/errors/opened/
```

``` json
[
  {
    "fixed": false,
    "id": 9,
    "registrationTimestamp": "2016-11-23T13:14:24.36+03:00",
    "source": "Other test project",
    "stackTrace": "Exception in thread \"main\" java.lang.ArithmeticException: / by zero\nat Test.main(Test.java:4)",
    "title": "Divide by zero"
  },
  {
    "fixed": false,
    "id": 10,
    "registrationTimestamp": "2016-11-23T13:16:42.826+03:00",
    "source": "Test project",
    "stackTrace": "Exception in thread \"main\" java.lang.NullPointerException\nat Test.main(Test.java:4)",
    "title": "Null pointer exception for string"
  },
  {
    "fixed": false,
    "id": 12,
    "registrationTimestamp": "2016-11-23T14:14:36.941+03:00",
    "title": "New title for some error"
  }
]
```


## Получить спсиок исправленных ошибок

``` json
GET http://localhost:8080/rest-error-tracker/api/errors/fixed/
```

``` json
[
  {
    "fixed": true,
    "id": 8,
    "registrationTimestamp": "2016-11-23T13:12:24.665+03:00",
    "source": "Test project",
    "stackTrace": "Exception in thread \"main\" java.lang.ArithmeticException: / by zero\nat Test.main(Test.java:4)",
    "title": "Divide by zero"
  }
]
```



## Добавить информацию об ошибке

``` json
POST http://localhost:8080/rest-error-tracker/api/errors/
```

**Тело запроса:**
``` json
{
	"title": "Some error",
	"source": "Test project",
	"stackTrace": "Exception in thread \"main\" java.lang.NullPointerException\nat Test.main(Test.java:4)"
}
```

**Ответ с кодом 201 Created:**
``` text
A new error has been added
```

## Изменить информацию об ошибке

``` json
PUT http://localhost:8080/rest-error-tracker/api/errors/11/
```

**Тело запроса:**
``` json
{
	"title": "New title for some error",
}
```

**Ответ с кодом 200 OK:**
``` text
The error has been updated
```


## Закрыть ошибку

``` json
POST http://localhost:8080/rest-error-tracker/api/errors/11/fix/
```

**Ответ с кодом 200 OK:**
``` text
The error has been fixed
```


## Удалить ошибку

``` json
DELETE http://localhost:8080/rest-error-tracker/api/errors/11/
```

**Ответ с кодом 200 OK:**
``` text
The error has been deleted
```
