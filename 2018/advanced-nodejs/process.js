// process is an event emitter

process.on("exit", p => {
    // do one final sync operation
    // before node process terminates
    console.info("terminating...");
});

process.on("uncaughtException", errorMessage => {
    // something went unhandled
    // do any cleanup and exit anyway

    // don't do just that
    // it will keep app running
    console.error("EXCEPTION:");
    console.error(errorMessage);

    // after manually handle it
    // FORCE exit the process
    process.exit(1);
});

// keep the event loop busy
process.stdin.resume();

// trigger an exception
throw "some bad issue";