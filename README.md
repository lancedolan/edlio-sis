# Edlio Student Information System!
An example project from Lance Dolan


Some notes for judge:
- Second time using Mongo, and the first time was a POC, so forgive any newbiew mongo anti-patters.
- First time using MongoDB with Heroku, absorbed ramp up time creating dev environment.
- I'm actually converting JSON to a POJO, and then later from POJO to JSON to store in Mongo, which is ideal for clean separation of concerns
and hiding implementation details away from client code... But it's really inefficient, and reminding me why I'm excited to dive deeper into node.js

Time-saving compromises I wouldn't normally make in a production quality solution:
- Not implementing partial-update in api, which would optimize bandwidth
- Not worring about dependency injection.. Guice/Spring seems overkill for this, and writing this bullet takes a lot less time than some elementary factory.