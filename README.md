# Edlio Student Information System!

This is an old example project I wrote while interviewing for Edlio, LLC in 2016. The goal was to quickly write a simple restful API that provides a small list of endpoints defined by Edlio. It isn't representative of my current skills.

Time-saving compromises I wouldn't normally make in a production quality solution:
- Missing javadoc on classes/methods
- Not implementing partial-update in api (PATCH), which would optimize bandwidth and simplify client code.
- Not worring about dependency injection.. Guice/Spring seems overkill for this, and writing this bullet takes a lot less time than some elementary factory. Prepare to see the 'new' keyword.
- model package has a loose dependency on dao impl in that it implements an interface given by the dao impl... thats a no-no. Saved me substantial time in developing the dao impl.
- would like to refactor DAO methods to take a class and id rather than object partially populated with id.
- Not using jackson to deserialize and map the JSON from REST client in to Jave POJOs. RestModelBuilder is a static method library that provides a rudimentary solution.
- "magic" String literals. I normally define String's to constant variables.
- PUT should create if the thing doesn't exist. It currently only updates if the thing exists.
- Api is missing response information on PUT to notify whether anything was successfully changed. Client code just relies on the 200 status code.
- Not fully validating all attributes of JSON 
- No authentication, wide open endpoints
