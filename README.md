[![CI-Tests](https://github.com/tony-zeidan/AddressBookApp/actions/workflows/CI-Testing.yml/badge.svg)](https://github.com/tony-zeidan/AddressBookApp/actions/workflows/CI-Testing.yml)
# Address Book Application
This project was created throughout the Software Engineering Lab course (SYSC 4806) at Carleton University.

This application uses the Spring Boot framework and Maven tooling. It simulates the functionality of Yellow Pages,
in that it allows the user to create many Yellow Pages books and add contacts to them.

The guidelines for the labs in particular are shown below.

## Lab 1 Guidelines

We're going to use the IntelliJ IDE this term, like we did in SYSC3110. But this
term we’re going to use the Ultimate Edition, which is still free for students.
So your first order of business is getting Ultimate Edition (we won’t need
Ultimate’s features for this lab, so if you already have the Community
edition you can still complete this lab, but make sure you get Ultimate
Edition for future labs).
Our goal in this lab is to create a simple AddressBook application, but this
time using Maven. There’s this 5 minute intro to Maven on the Maven web
site you may want to check out first.
1. Find and Open IntelliJ. Click "create new project"
2. In the window that pops up, specifically make sure:
   o your project location is where you can re-use your work in
   future labs;
   o your build system is Maven;
   o and the JDK is at least 14.
   In the Advanced Settings at the bottom of that window, you can set the
   GroupId and the ArtifactId if you wish (those are parameters of Maven
   that were also mentioned in the 5-minute intro to Maven linked above,
   and which you can always change later in the pom.xml). Click "Create".
3. On the left pane, you can now see the folders and files that were created
   for you: among others a src/main folder, and a corresponding src/test
   folder. You should also see the generated pom.xml file. Select that file to
   see its content and verify that it does contain the GroupId and ArtifactId
   you provided.
4. Now let's see what we can already do with our Maven project. In the
   View menu, select "Tool Windows -> Maven ". In the new pane, after
   2
   clicking on the small arrow next to your project name, you can see all the
   phases of the lifecycle, ready to be executed. Try executing the "package"
   phase, by double-clicking on it. Maven should go through all phases up to
   "package", and so it will compile (well, there's nothing to compile so far),
   run the tests (nothing here either), and create a jar file for you!
5. Fresh from this success, now create your AddressBook program, like you
   did back in SYSC3110: AddressBook is a class that has-a list of BuddyInfo
   objects, which each contain the name and phone number of your
   buddies:
   - Right-click on the package (named after the groupId you
      provided earlier) inside the java folder in the Project pane, and
      select "new->class", to create BuddyInfo and AddressBook.
      Double-check that those classes are located inside the package
      and have the “package” declaration at the top of their source
      code (creating these classes in non-default packages will save
      you headaches down the road…). Populate those classes with
      the variables and methods they require.
   - IntelliJ provides a lot of contextual help. To access it, select the
      AddressBook class name in the source, and hit alt-enter. It will
      offer you many options, among which the possibility to create a
      corresponding test class. Do it! Be careful to select JUnit 3 or 4
      as the target. If IntelliJ doesn’t recognize the JUnit imports or
      keywords, it could be that your Maven POM is missing a
      dependency on JUnit. Clicking “fix” should do the job. The
      pom.xml file should now have the dependency on JUnit (check
      it!) If not, go back to the Maven link included at the beginning of
      this lab to manually fix the issue by adding the dependency.
   - Similarly, selecting the class name and clicking alt-insert will
      provide plenty of automatic code generation options. Feel free
      to use them but always inspect the result to see if it conforms to
      what you wanted.
      
   - Complete your basic AddressBook application, with a main()
   method that simply creates an AddressBook object, populates it
   with a couple of BuddyInfos and prints the content. Provide unit
   tests for both AddressBook and BuddyInfo. Using Maven,
   package your deliverable into a JAR.
   o Show your work to the TA and put the entire directory in a zip
   file and submit on Brightspace. 

## Lab 2 Guidelines
The goal of this lab is to use object-relational mapping, specifically JPA, to
persist our address book and its list of buddies to a database. First, you need
to read this article, all the way down to (but excluding) the section called
“Reference Implementation”. Armed with this knowledge, you should
inspect the code in this example of using JPA to persist to a SQLite database
that you can refer to and borrow from.
First, recall the layered cake that is ORM:
• First, you need a database. In this lab we’re going to use SQLite, which
doesn’t require any client-server setup and just stores things in a
single file. H2, an in-memory database, is another simple option,
which we will explore in future labs.
• Next, you need a database-specific JDBC driver so that you can query
the database from inside your Java program.
• JPA is an ORM layer on top of JDBC, but it really is only a standard. You
need an actual implementation of JPA. For this lab we will use the
EclipseLink implementation, but in future labs we will use Spring’s.
Trying things with different libraries and tools is a good exercise in
figuring out the “magic” that happens behind the scenes!
• Finally, the Java classes need to be annotated with the appropriate JPA
tags.
So, here are the various steps to follow:
1. Once you’ve loaded your AddressBook project in IntelliJ, edit the Maven
   pom.xml file to include the dependencies on the SQLite JDBC driver and
   the EclipseLink implementation of JPA. See the pom.xml of the example
   linked above to get the dependencies right. Every time you change the
   pom.xml file, you need to reload the project, to download and install the
   new dependencies. Press ⇧⌘I (macOS), or Ctrl+Shift+O
   2
   (Windows/Linux), or click the little Maven icon to get it to
   download and install the dependencies. Or in the Maven tool window,
   right-click on your project and select “Reload project”.
2. Next, you need to set up a configuration file called persistence.xml
   and that you have to save in the folder called “META-INF”. Maven may
   have already created this folder for you, but if it didn’t, make sure to
   create the META-INF folder inside another folder called “resources”
   which itself should live inside the “src/main” folder. If you had to
   manually create the “resources” folder, mark the folder as a “Resources
   Root” by right-clicking on the folder and selecting the “Mark Directory as”
   option, which then offers you the “Resources Root” option. The content
   of the persistence.xml file can be adapted from the example in the
   link provided above. You can either create this file manually (use new ->
   file and type or copy-paste what you need in that file), or you can rely
   on IntelliJ to help you. For the latter option, right-click on your project,
   choose “add framework support”, then choose “JavaEE persistence”1
   . It
   will ask for a version of persistence.xml; pick 2.1 or 2.2. It will then ask for
   a provider, for which you can specify “EclipseLink”. No need to do
   anything else with the wizard. Either way, at this point you should have a
   persistence.xml file in the META-INF folder, and you need to modify the
   file for your purpose. We’re going to see how in the next step.
3. Let’s look at the persistence.xml file of the example in the second link
   above more closely. It defines a persistence unit (the name you give it is
   up to you, just know that you will refer to it later when you will invoke it
   in your java code, and to be safe don’t use spaces or special characters in
   this name), which is comprised of:

   - the persistence provider (that’s the full name of the class that
   implements JPA for you. If you used the wizard it probably set
   this for you already, and it should be identical to what is in the
   example). Just make sure that the transaction-type of your
   persistence unit is set to "RESOURCE_LOCAL" just like in the
   example. This is to specify that we are using persistence in a
   standalone desktop application.
   - the list of classes to be persisted. You’ll need to modify this part
   with the full class names (including package name) of your
   AddressBook application;
   - then there’s a set of properties to configure parameters that the
   persistence provider requires, i.e., the name of the JDBC driver,
   the location of the database, etc. These values should be
   identical to the ones in the example. Since we’re using SQLite,
   we won’t need to specify a username or password to access the
   database.
4. Now you can start annotating your AddressBook classes. Let’s start small,
   and first only annotate and persist a BuddyInfo. You can use the
   Product.java class provided in the example for inspiration. You basically
   need to specify that BuddyInfo is an @Entity, and you need to provide a
   primary key identifier using the @Id tag. IntelliJ will help you import the
   right packages so that the tags are recognized. Some JavaBeans
   conventions will need to be respected: make sure to provide a default
   (i.e., no-arg) constructor (if you don’t have any constructor at all you are
   fine, but if you only have non-default constructors you should also
   provide a default constructor), make sure your accessors and mutators
   are following proper getXXX and setXXX conventions.
5. Let’s now test that we can persist a BuddyInfo object! In a separate class
   (how about a unit test?), create an entity manager (see the JPATest class
   in the example), and, within a transaction (see example again), use the
   entity manager to persist instances of your annotated class. Now you can
   query the table to see if you can retrieve those objects you just persisted.
   In JPA, the query still needs to be written using a SQL variant, but in other
   ORM (such as Active Record in Rails, and to some extent in Spring) this is
   not usually necessary. In fact, even configuration files like persistence.xml
   are often not necessary when using a full-blown framework and the
   power of introspection and conventions, as we will see very soon!
   - Problem-tracking tip #1: if IntelliJ doesn't recognize your
   annotations, it could be because you haven't updated Maven
   with the dependencies on EclipseLink properly.
   - Problem-tracking tip #2: double-check that the persistence unit
   name you used when invoking the EntityManagerFactory
   matches the one you specified in your persistence.xml. Make
   sure the name you used doesn’t contain spaces or other nonalphanumeric characters.
   - Problem-tracking tip #3: when building, Maven copies files
   stored in the "resources" folder over to the target folder. This
   means it may not pick up the persistence.xml folder if it is in the
   src folder. Two solutions:
     - Copy it into the resources folder so that the build works.
     - Or tell Maven where to look, using the <resources> tag .
   - Problem-tracking tip #4: some students have reported a "not
     known entity" error. This could be because in very recent
     versions of Java, the javax.persistence.* package (and more
     generally, libraries that are part of Java EE) has been moved to
     jakarta.persistence.* So the easy fix would be to use an older
     version of Java (Java 17 works for me), and the more
     complicated but more sound fix would be to use the newer
     packages (and corresponding Maven dependencies, if needed).
6. You made it here? Congratulations, this means you successfully persisted
   a BuddyInfo object! Now let’s annotate AddressBook so that we can
   persist an entire address book containing many buddy info objects. You
   can use the example in the first link above for inspiration, but basically,
   you need to capture, using annotations, the fact that an AddressBook has
   a one-to-many relationship with BuddyInfo. If you do this successfully,
   you should be able to persist a few buddy infos, add them to your
   address book object, then persist the address book itself.
7. Bonus round: it is possible to create an address book first with non-yet persisted buddyInfo objects, in such a way that when you persist the
   addressBook it also persists its buddyInfos. For this to work you need to
   investigate the “CascadeType” of your one-to-many annotation. It’s just a
   one-liner; can you get this to work?
   When you are done, show your work to the TA and put the entire directory in
   a zip file and submit on Brightspace.

## Lab 3 Guidelines
Part I – Accessing Data with JPA using Spring Boot
Go through https://spring.io/guides/gs/accessing-data-jpa/ first (see
https://spring.io/guides for a whole slew of very nice and simple tutorials
that will be very helpful for your projects). The tutorial uses Spring and the
H2 database for JPA support and persistence instead of EclipseLink and
SQLite. It also uses the notion of Repositories to provide some built-in
methods to access the database via Java (thus reducing the need for JPQL).
Finally, you’ll notice that there’s no persistence.xml file involved (and so you
should delete it to avoid any possible undesired interaction and confusion)!
All thanks to the magic of Java reflection and convention over configuration!
So now let’s switch technologies and apply this tutorial to replace the
AddressBook persistence work you did in the previous lab, but this time
using Spring Data JPA repositories and the in-memory H2 database. It should
look much nicer!
When you’re done, show your work to the TA, or if you weren’t able to,
upload a zip file of the work.
Part II – OPTIONAL: no need to show to the TAs, but they can help you
Part I shouldn’t take too long to do, so with the time you have left you can
attempt this more challenging exercise, which is optional but will get you to
understand in more depth what Spring’s Dependency injection can do, by
applying it in the simpler context of a desktop application that uses Swing
for its UI.

So first, in a separate project (you won’t be reusing what you do here in
future labs) come up with a very simple Swing UI for your AddressBook
application, where you can interactively populate a BuddyInfo and save it to
your address book. Use MVC principles as per SYSC3110! The gluing
together of the various UI components, as well the registration of the View
to the Model, the Controller to the View, etc., should happen, as much as
possible, in some main() method. This is done in order to reduce the
dependency of the various components to one another. In the next part, this
configuration that happens in the main() method will be done using
Dependency Injection instead.

Now read this tutorial (it is very old, and was rescued by TA John from the
wayback machine!) You can skip the environment setup parts, since you’re
going to use Maven and just add the Spring dependency in the pom.xml file
there. The meat of the article starts at the section entitled “Creating the to-do list”. It shows how dependency injection can be used to assemble the
components of a Swing application. It’s not necessarily a great idea to use
Spring and XML files for such a purpose (one could assemble these
components using plain Java in a separate class), but it will help give you a
concrete feel for what DI does. You can skip the last part of the article that
talks about the Spring Rich Client Project.
Now to try out what is described in the tutorial: try gradually replacing the
“glue” code in your above-mentioned main() method (e.g., where you put
together the UI and the MVC) with configuration code, using an XML
configuration file or the more modern annotations. See how far you can go
in replacing code with configuration! Important: don’t try to blindly copy paste what you find in the tutorial!!! Don’t try to mimic the UI of the
tutorial!!! That will only create frustration.

## Lab 4 Guidelines
In this lab we are finally ready to build a simple Spring MVC AddressBook
App, basing it on Part I of Lab 3.

First, provide controllers that allow a user to create an addressbook, and
add/remove buddies. These controllers should be REST controllers
consuming and producing JSON-formatted data. Remember that
@CrudRepository provides you precisely with such REST endpoints, so you
might already have what you need! Alternatively, you can program your
own @RestController class. You should be able to interact with your app
using curl or some other command line tool, tools such as Postman or
IntelliJ’s own tool (go to tools->HTTP client). This tutorial shows you how
you can compose your HTTP requests to interact with those REST endpoints.
Here’s another one that uses curl.

Then, provide controller(s) that return a rudimentary GUI view using
Thymeleaf to list the buddies for a given addressbook. No need at this point
for the view to be interactive and let the user submit a form or anything like
that. This brief Spring tutorial will be helpful (pay attention to the
dependencies you need to add in your pom.xml), and here’s a more in-depth tutorial on Thymeleaf if need it.
Optional for this week: if you’re all done, feel free to test your app now! This
more comprehensive tutorial shows how to write simple unit tests and
integration tests for your Spring MVC app.

When you’re done, show your work to the TA. Demonstrate how you can
compose HTTP requests to interact with your controllers using curl, Postman
or IntelliJ’s http client tool. Show also the GUI that displays the content of
your address book.

## Lab 5 Guidelines
If you’ve made it this far, congratulations: you have turned your
AddressBook program into a very simple web app! In this lab you are going
to set up the continuous delivery pipeline for your app, so that it is tested
and built on every commit, deployed, and easy to update. Here are the steps
to follow:
1. First, if you haven’t already done so (should be second nature by
now!) enable git version control for your project, and add a
corresponding remote GitHub repo from IntelliJ. You should now be
able to commit your changes and push them to GitHub.
2. Let’s write some tests, to replace and automate the manual creation
of HTTP GET or POST requests, and the verification of their response.
This tutorial shows you how you can run an integration test of your
web app, and also a lighter way, using a mock, to run those same tests
without having to start up a web browser every time. Look into
RestTemplate for the programmatic composition of REST calls. If your
tests pass, move to the next step.
3. Now set up continuous integration on GitHub. For this we are going to
use GitHub Actions (on a free GitHub account, you get 2,000 minutes
free per month). First, read this to acquaint yourself with GitHub
Actions, then follow this to set up the Java with Maven CI workflow for
your repository. The “Actions” tab in your GitHub repo page should
normally also recommend the “Java with Maven” starter workflow to
you, and choosing it will generate the YAML file and store it in the
right directory for you. But make sure the content of the YAML file it
generates is what you want! For example, check that the JDK version it
uses is the same as the one you have set in your pom.xml file. Also, it
might include additional steps that might require GitHub permission
tokens, and that we don’t need to worry about here. Now, if you push
your code along with the tests you wrote on step 2 to GitHub, your
GitHub Actions workflow should automatically run those tests and
build your app if the tests pass. Check if that’s the case by going to the
Actions tab of your GitHub repo. You can even include a badge that
displays the status of your build in your repo’s README.md file! Figure
out how to do it!
4. Unfortunately, Heroku no longer offers a free tier for hosting web
apps, and the other free options require a credit card, so for this year
at least, the following is OPTIONAL (and the TAs have not been asked
to offer support for this): see if you can create another GitHub Actions
workflow to deploy your application to a cloud hosting service! For
Java applications, currently Google Cloud and Azure offer free hosting
(but both require credit card information). Other free hosting services
such as render.com require that you build a Docker container first.
This link documents some of your options. Now try adding a simple
feature to your address book, and test it. For example, add an address
field to your BuddyInfo and write the corresponding test(s). Push your
changes and see if your deploy shows the updated version
Success? Show your work to the TA, i.e., show your repo with a successful
build involving a few integration tests. If you can’t, invite your dedicated TA
as a member of your GitHub repo (the GitHub handles for the TAs can be
found on Brightspace in the “Course Details” section). The TA will check that
you have correctly set up the GitHub Action for CI, and that there are a few
integration tests that are being run by CI.

## Lab 6 Guidelines
Now it’s time to provide a functional frontend for your AddressBook app!
**Part 1**: the more traditional client-side
(note: you may have already done some of this in past labs!)
Using HTML static pages and/or template views (with Thymeleaf for
example), create web pages and/or forms to allow the user to create an
address book, to populate it with buddy infos, and to list the current content
of your address book. This means that you need to provide controllers on
the server-side to handle the client requests and to render the relevant
views. This tutorial may again be helpful.

**Part 2**: the Single Page Application (SPA) version
(note: again, you may already have done some of this already)
If you have provided Spring Data repositories for your AddressBook and
BuddyInfo JPA models, (see this tutorial for example), then you already have
a set of RESTful controllers for your application.
The JSON objects returned by these controllers can be used by a Single Page Application on the client
side.

So now you should be able to write the JavaScript functions that will make
an AJAX call to the appropriate controller on the server, with a
corresponding JavaScript callback function that will use the returned JSON
response to modify and render the current HTML page. These functions can
then be registered to the relevant submit action (a button press or other).
This tutorial may or may not prove useful.

To keep things DRY, the controllers you wrote in Part 1 should probably just be delegating to
the RESTful ones. The only added value should be to select and populate the view to render.

This registration process should be done on load time. This way, if the
browser doesn’t support JavaScript, your client-side will still behave
correctly as per Part 1. This is the “progressive” way!
Done? Are you a frontend wizard now? Show your work to the TA! If you
can’t, upload a link to your GitHub repo containing your work for Part 1
and Part 2. 