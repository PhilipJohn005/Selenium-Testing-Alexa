let lastRecognizedText = ""; // Track the last recognized text to prevent repeated responses
let isListening = false; // Track the listening state to avoid multiple starts

// Handle user commands
function handleUserInput(inputText) {
    if (!inputText || inputText === lastRecognizedText) return; // Prevent repeated recognition responses
    lastRecognizedText = inputText; // Save recognized input

    if (inputText.includes("what is an apple")) {
        speak("An apple is a fruit that is sweet and crisp.");
    } else if (inputText.includes("what is your name")) {
        speak("I am Alexa, your personal assistant.");
    } else if (inputText.includes("how are you")) {
        speak("I am just a program, but I'm here to assist you.");
    } else if (inputText.includes("weather")) {
        speak("I don't have real-time weather updates, but you can check a weather app.");
    }else if(inputText.includes("what is my name")){
		speak("Your name is Philip.");
	} 
	else {
        speak("Sorry, I did not understand that.");
    }
}

// Initialize speech recognition
function init() {
    if ('webkitSpeechRecognition' in window) {
        const recognition = new webkitSpeechRecognition();
        recognition.continuous = true; 
        recognition.interimResults = false;
        recognition.lang ="en-US";
        window.recognition =recognition;

        recognition.onresult = function (event) {
            try {
                const recognizedText = event.results[event.results.length - 1][0].transcript.toLowerCase();
                console.log("Speech recognized: ", recognizedText);
                handleUserInput(recognizedText);
            } catch (error) {
                console.error("Error processing recognized text:", error);
            }
        };

        recognition.onerror = function (error) {
            console.error("Speech recognition error:", error);
        };

        recognition.onstart = function () {
            console.log("Speech recognition started...");
            isListening = true;
        };

        recognition.onend = function () {
            console.log("Speech recognition stopped.");
            isListening = false;
            recognition.start(); // Restart if stopped
        };

        window.recognition = recognition;
    } else {
        console.log("Speech recognition is not supported.");
    }
}

// Function to handle starting listening only once
function startListening() {
    if (window.recognition && !isListening) {
        console.log("Starting voice recognition...");
        window.recognition.start();
    } else {
        console.log("Speech recognition already active or not initialized.");
    }
}

// Text-to-speech
function speak(text) {
    const msg = new SpeechSynthesisUtterance(text);
    window.speechSynthesis.speak(msg);
    console.log("Speaking: ", text);
}
