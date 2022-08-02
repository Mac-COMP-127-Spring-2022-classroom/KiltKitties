# KiltKitties

For COMP127: Object-oriented programming

Francesca, Jay, Michael

When we set out on this project, I think we anticipated having some sort of award/achievement system so that the user could see their progress in the evolution of their cats. However, this was still something we knew we could forfeit if needed. Unfortunately we did end up forfeiting this, but I think we were able to make up for it in other ways. For example, another one of our contingency plans was to make fewer genes/variations if necessary. We did not have to end up limiting ourselves in this way as we have 297,910 possible different cats. 

In order to actually use our program, it’s pretty simple. Once you open up the project in VS Code, all you have to do is run the WindowManager class, which is displayed at the bottom below all the other classes. After you run it, our canvas will pop up, and there will be three buttons: Buy New Cat, Evolve, and Sell. In order to generate a new cat on the screen and purchase it (each cat costs 100 and you will see your currency go down) you simply press the Buy New Cat button. In order to sell a cat, you must have one and only one cat selected using the Select buttons beneath each cat. If a cat is selected it will have a green rectangle around it. If you would like to create a child cat of two others, simply select 2 cats and hit the Evolve button, and the new child cat will appear on the screen. You can always deselect a cat by pressing the select button again. You can never have more than 10 cats, so you will have to sell before buying/evolving more if you reach the capacity. You can always see how much currency you have available by looking at the bottom of the screen in the center.

The most challenging part overall was the delay we were experiencing with our images. In order to get our images to properly appear, we’d have to insert a long canvas pause. We thought that the pause was a good enough remedy even though it was annoying, but after presenting and staying after class, Ben Hillman was able to figure out how to fix the delay and another issue that was occurring due to the naming of our files, specifically the repeated use of names after cats had been sold. That issue was causing old cats that had already been sold to appear on the screen instead of the new ones with that same name.

Our project was largely inspired by CryptoKitties, and we replicated their color palette. We received help from Timothy Lang for debugging the positioning of the cats in our user interface, and help from our professor, Ben Hillman, in fixing the delay in the images and the issue with the displaying of older files that have the same name as the new ones. 


