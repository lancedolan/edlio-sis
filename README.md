# Edlio Student Information System!
An example project from Lance Dolan


Some notes for judge:
- Second time using Mongo, and the first time was a POC, so forgive any newbiew mongo anti-patters.
- First time using MongoDB with Heroku, absorbed ramp up time creating dev environment.
- I'm actually converting JSON to a POJO in the service layer, so that it can then be passed to the DAO and converted back to JSON. The same redundant operation is done in reverse during GET operations. It's ideal for clean separation of concerns and hiding implementation details away from client code... But it's really inefficient, and reminding me why I'm excited to dive deeper into node.js
- It might be strictly more restful to allow client to POST new
objects to their exact URI, such as /api/class/123123, however I've decided to allow the backend to safely generate ID, since we're dealing with IDs and not titles/labels.

Time-saving compromises I wouldn't normally make in a production quality solution:
- Missing javadoc on classes/methods
- Not implementing partial-update in api (PATCH), which would optimize bandwidth and simplify client code.
- Not worring about dependency injection.. Guice/Spring seems overkill for this, and writing this bullet takes a lot less time than some elementary factory. Prepare to see the 'new' keyword.
- model package has a loose dependency on dao impl in that it implements an interface given by the dao impl... thats a no-no. Saved me substantial time in developing the dao impl.
- would like to refactor DAO methods to take a class and id rather than object partially populated with id.
- Not using jackson to deserialize and map the JSON from REST client in to Jave POJOs. RestModelBuilder is a static method library that provides a rudimentary solution.
- String literals all over the place. I never do that in production code.
- By definition, PUT should create if the thing doesn't exist. It currently only updates if the thing exists.
- Api is missing response information on PUT to notify whether anything was successfully changed. Client code just relies on the 200 status code.
- not fully validating all attributes of JSON 
- No authentication, wide open endpoints