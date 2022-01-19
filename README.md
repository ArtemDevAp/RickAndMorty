
# Introduction

Please imagine that a very significant client wants to become our partner. He is a huge fan of Rick and Morty, and he tests different companies with a simple PoC app that will be a seed for the future project. When the client sent the specification and asked us to start, you were on holiday. There were no free developers, so the Project Manager decided to write this app by himself with the assistance of a Content Specialist and the Android trainee.

The scope of the app is not very vast it contains the following features:

- List of episodes
- List of characters
- List of locations

You can find more about api which was used on: https://rickandmortyapi.com

What they managed to implement is:

 - Episodes activity
 - Basic dagger config for the networking, retrofit config, etc

Now... What's your role:
You return from your vacation, you see this mess, and you are asked to finish it. You are a solid Android Guy, and I'm sure you will save this project.

# Tasks
Tasks are **NOT** ordered by their priority - do as many as You want, keeping in mind that we value quality over quantity. It's better to do fewer tasks but showing your skills, avoiding errors and keeping good practices. After providing us with the solution, you can add some general notes what else would you do if you had more time/how would you approach things/what else would you test etc.

- As you know, not very experienced guys started this project, so they downloaded some adapter from the web (StackOverflow Driven Development), but something went wrong. There are only a few episodes, please fix that the app will fetch more episodes when user scrolls down the list.

- Add Bottom Navigation, so the user can choose what he wants to see. Bottom Navigation should allow displaying Episodes, Characters, Locations screens.

- There is no error handling when performing the API call. Please handle potential errors and present snackbar with the error message to the user.

- There is no loader when a user opens any screen - please add it, so a user can see something before the data is downloaded.

- There is no loader when a user scrolls down. Add new view type to the -adapter, so the progress bar is visible when another page is being downloaded.

- Handle the state. We don't want all the data to be gone when the user rotates the screen.

- Refactor - Show off. Trainee didn't know MVP/MVVM/MVI or any other design patterns. It's also the beginning of the potentially huge project. Refactor it so that you can write some unit tests. You can move classes to separate files, change the packaging, use any library you want, extract constants from XML. Just show how you would do it.

- Tests - Unit (or instrumentation) tests  are always nice to see in the project. You can also write empty tests without implementation to show us what cases you would test.

## Good luck :)
