# ClickBus Rest API Application

This is a simple Web Application that applies API building concepts using Java and SpringBoot.

The data being viewed and recorded is stored in memory only and will be reset at each system reboot.


## Running
In order to run the application, execute the Main Java class located at **project-dir**\quero-ser-clickbus\testes\backend-developer\solution\src\main\java\com\click\bus\clickbus\ClickbusApplication.java


## Populating data
**host** = <http://localhost:8080>                        if you're running it in your machine

**host** = <https://canovasclickbusjava.herokuapp.com>    if you're using the test environment available at Heroku

To insert a Place, make a Post request to **host**:/places/

The parameters are:

name **required**, slug, city and state


If you want to update a specific place, just post the information again while forming the same name.


<img src="https://imgbbb.com/images/2020/01/22/postmanapiexample.png">


## Searching

To see all the places registered, you can access the URL **host**/places/


Alternatively, it is possible to filter the result by name:

**host**/places/list?filter=**part of the name**


To retrieve information of a specific place, do a GET request to:

**host**/places/id/**exact place name**