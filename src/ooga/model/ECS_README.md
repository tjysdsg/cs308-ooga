ECS Note by Jiyang:

Entity is a bridge between components and system, it doesn't have much data. We can treat GameObject
as an entity, or let the game object have an entity instance.

Component contains most of the data. I was wrong about putting the logic here.
System should take care of the game logic. Components should only contain data.

Systems take care of all the game logic. They also handle creating, accessing, updating,
and removing entities, components and other stuff.

I plan to implement ECS by imitating the designs in https://github.com/junkdog/artemis-odb.

And I'll update this file along with way to explain things more clearly.