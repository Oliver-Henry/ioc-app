# IoC Container Test
We would like you to write a simple IoC container in Java or Kotlin. We don’t want you to spend too much time building a complex solution, but it’s up to you how much time you spend on it. So, keep it as basic as possible. For example, it is okay to support only transient objects and classes (no constants, functions and other types), but it would be interesting to see how you deal with circular dependencies. The solution doesn’t necessarily have to be an ‘app’, but a solution in pure Java/Kotlin. You don’t need to focus on extensive test coverage either, but we will definitely talk about it during the interview to follow.
We would like you to focus on:
- building a library and demonstration how to use it
- basic dependency resolution functionality
- good code architecture
- extensibility
- writing testable code
- easy integration and good developer experience (if this was a real project, it would be consumed by other developers)

## Initial Dependency set-up
    -Predefined Dependencies have to be created e.g. creating the set up for a network call

## Application Class
- An ``Application class`` needs to be created and declared in the ``manifest file``.
    - In the onCreate of the app class an instance of the 'DIGraph' class needs to be created by calling its ``getContainer()`` function
    - In the app class an additional function is required to set up the dependencies in the ``DIGraph`` class, this function will have a parameter for the ``DIGraph`` class which will have been created from the ``getConntainer()`` function called eairlier.
    - With the passed in instance of the ``DIGraph`` call its function ``installDependency(Class<?> dependency)`` passing in the class housing your dependency.
    - The function ``installDependency(...)`` should be called once for each of your dependencies.
    - Finally in the app class back in the ``onCreate`` function the ``DIGraph``'s function ``generateGraph()`` should be called.

The ``generateGraph()`` function will check your dependencies passed in earlier from the ``installDependency()`` function and then will create a single instance of each of your dependancies and save it for later.

## MainActivity Class
- To get an instance of one of your dependencies in your ``MainActivity`` or where ever you need it,
    - Create an instance of that dependency and assign its value with ``DIGraph.getContainer().inject(DEPENDENCY_CLASS_NAME.class)``
    - This will use the single instance of the dependency that was created earlier.