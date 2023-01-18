# Testing Assignment software Moodle using Provengo
This directory contains the Provengo project for testing $$*TODO* software name$$.

$$*TODO* 
1. replace the name of the folder 'helloprovengo' with the name of your software (use underscore/title case to avoid spaces)
2. Search and replace for the word 'helloprovengo' in the entire project and replace it with the new folder name. 
$$

## Running the tests
To run a single random test, run:
```shell 
provengo run helloprovengo
```

See the [Provengo README](helloprovengo/README.md) for more useful commands.

## API
See [Provengo README](helloprovengo/README.md) for a short description of the API.
For a full documentation go to [https://docs.provengo.tech](https://docs.provengo.tech)

## How we tested:
1. We started by creating the [story files](helloprovengo/spec/js/OrderPizza.story.js).
2. We then ran the following command to generate the test model:
```shell
provengo analyze -f PDF helloprovengo   
```
3. We repeated steps 1-2 until we were satisfied with the generated model.
4. We added [Event definitions](helloprovengo/spec/js/OrderPizza.EventDef.js) to define how the stories actuate the website using Selenium.
5. We used the following command to run the tests:
```shell
provengo run --show-sessions helloprovengo
```
6. We repeated steps 4-5 until we were satisfied with the result.
7. We recorded a video of the running tests and added it to the report. Since more than one browser session was opened, we recorded the entire screen. The link for the video is [here]($$*TODO* write the link$$).
8. We copied the generated graph of the model to a file named [model.pdf](model.pdf) inside this directory (the link should work).

### Story files
The test stories are in [Tests.story.js](helloprovengo/spec/js/OrderPizza.story.js). See the file for a detailed description of the stories.


### Events definition files
The specification for the events are given in [EventDef.js](helloprovengo/spec/js/OrderPizza.EventDef.js). See the file for a detailed description of the events.

