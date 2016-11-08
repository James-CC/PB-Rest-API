REST API built for existing MySQL DB. Utilizes Spring Boot and Hibernate to expose endpoints for three existing tables.

Edit application.properties with the database url, username, and password (located in src/main/resources/). Easiest way to run will be to export as a .jar via Maven or install Spring Tools Suite and run in the IDE.

Returned messages will be JSON and the parameters accepted is application/json.

Endpoints

----
/company
GET - returns a list of all companies

/company/new
POST
{"entityId": String (unique, required),
"pbId": String (unique, required),
"companyName": String (required),
"webUrl": String (unique, required)}

/company/edit/{entityId}
PUT
{"pbId": String (unique, optional),
"companyName": String (optional),
"webUrl": String (unique, optional)}

/company/entityid/{entityId}
GET - returns the company row with given entityId
DELETE - deletes the company row with given entityId

/company/pbid/{pbId}
GET - returns the company row with given pbId
DELETE - deletes the company row with given pbId

/company/weburl/{webUrl}
GET - returns the company row with given webUrl
DELETE

----
/sitestatus1
GET - returns a list of all sitestatus1 entries

/sitestatus1/edit/{id}
PUT
{"code1": String (valid, required),
"code2": String (valid, required)}

/sitestatus1/either/{either}
GET - returns a list of all sitestatus1 entries with given either

/sitestatus1/entityid/{entityId}
GET - returns a list of all sitestatus1 entries with given entityId
DELETE - deletes all sitestatus1 rows with given entityId

/sitestatus1/id/{id}
GET - returns the sitestatus1 row with given id
DELETE - deletes the sitestatus1 row with given id

/sitestatus1/new
POST
{"entityId": String (must exist in Company, required),
"code1": String (valid, required),
"code2": String (valid, required)}

/sitestatus1/pbid/{pbId}
GET - returns a list of all sitestatus1 entries with given pbId

/sitestatus1/url/{url}
GET - returns a list of all sitestatus1 entries with given url
DELETE - deletes all sitestatus1 rows with given url

----
/sitestatus2
GET - returns a list of all sitestatus2 entries

/sitestatus2/companyname/{companyName}
GET - returns a list of all sitestatus2 entries with given companyName
DELETE - deletes all sitestatus2 rows with given companyName

/sitestatus2/edit/{id}
PUT
{"code1": String (valid, required),
"code2": String (valid, required)}

/sitestatus2/either/{either}
GET - returns a list of all sitestatus2 entries with given either

/sitestatus2/entityid/{entityId}
GET - returns a list of all sitestatus2 entries with given entityId
DELETE - deletes all sitestatus2 rows with given entityId

/sitestatus2/id/{id}
GET - returns the sitestatus2 row with given id
DELETE - deletes the sitestatus2 row with given id

/sitestatus2/new
POST
{"entityId": String (must exist in Company, required),
"code1": String (valid, required),
"code2": String (valid, required)}

/sitestatus2/pbid/{pbId}
GET - returns a list of all sitestatus2 entries with given pbId

/sitestatus2/weburl/{webUrl}
GET - returns a list of all sitestatus2 entries with given url
DELETE - deletes all sitestatus2 rows with given url
