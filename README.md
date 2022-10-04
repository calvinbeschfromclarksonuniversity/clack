# clack
Like Slack but clacky. Truly the pinnacle of datafication.

Things to mention in report:

Had to use Objects#equals in any comparisons in the equals methods of my various classes that would possibly include
nulls, because Objects#equals can handle nulls but you cant call equals on a null reference.

FileClackData instances can be initialized with types other than 3 because the type variable doesn't really do anything.
Same for MessageClackData and being initialized with type 3.
