# Project-Updater
How to update Intelbras devices(AP 300, HotSpot 300, WOM 5000, WOM 5000i and WOM 5000 MiMo) in mass production.

The main go of this project is to be able to update a lot of devices of different firmware/products versions using a unique software that do everything automatically.

to give you a quick guide how this program works, lets make a workflow of each step that the program do to update a device:

1° Check if the device is alive (socket to the IP, using the http port);

2° If it is alive, try to login and from the html response, gets the product version;

3° Knowing the product version, choose the firmware file related to the product version;

4° Send the file using Post method;

5° Wait until finishes the file transfer, give a timeout of 5 seconds and end the connection. At this point, the device are processing the file received from memory ram to flash. **maybe the device stills up for a couple of seconds because it's processing the file, and it's important to not reboot the device at this time, because you could damage the device.**

# Suggestions of getting this code better:

 * 1° Rework all the WindowApp class to be able to separe in other classes all functions related to fields validation;

 * 2° Separate in other class the function of list of Ips and Range of Ips;

 * 3° Create a way to import a file with a list of Ips, reade each line of the file, and for each one, start the logical discribed at the workflow guide;

 * 4° Create separated Threads for each update, to be able to update more then 1 device at the same time.

for sure there will be more suggestions that this ones that I have mention, and feel free to do it.

And just remember that this project only works(for now) on Intelbras products(AP 300, HotSpot 300, WOM 5000, WOM 5000i and WOM 5000 MiMo).

**For now, this is it, and remember to test it first using one product of each version to be sure that is working. I do not take any responsability of anyone using this software, and if you want to use it, you are accepting possible risk that could damage your device.**
