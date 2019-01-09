# GithubRepos

## Author

*OUANAOU Anas - Final year software engineer student at EMSI (Ecole Marocaine des Sciences de l'Ingénieur)*

## Description

This small **Android** application has been developed as part of a mobile coding challenge in order to get a 6 months internship at United Remote in 2019.

The idea of the task is to implement an application that will list the most starred Github repositories that were created in the last 30 days, using the Github API.

## About the API

In order to create the desired URL, here are the parameters to keep in mind :

* **Query parameter** : Must receive the date 30 days before the current one [created:>2019-01-09]
* **Sort parameter**  : Repositories will always be sorted by "stars"
* **Order parameter** : By default, we must research repos in a descending order "desc", but the user can change the order to and ascending one
* **Page parameter**  : This parameter is needed to request more data from the next page when scrolling down (Pagination)

**Note :**  API only accepts **10** requests from the same IP adress per minute, and it returns 30 repositories per page.

More information about Github API can be found [here](https://developer.github.com/v3/search/#search-repositories)

## How to run it

The source code for the application can be found [here](https://github.com/Pinia06/GithubRepos)

Once cloned, the code source can be opened using Android studio in 3 simple steps :
1. Open Android Studio
2. Choose 'Open an existing Android Studio Project'
3. Pick the folder downloaded based on the chosed destination

This application was developed under Android studio 3.2.1 and was compiled under android 28 SDK version, and accepts a minimum of 21 SDK version.

## Technology and libraries used

The programming language that has been used to develop this application is **Java**. 

The libraries and APIs implemented in the project are : 
	
* **Retrofit + GSON as a converter** 
		For the simplicity, ease of use, and compatibility with the paging library.

* **Design API**
		For adding the bottom navigation material design. 

* **Glide framework** 
		To get the avatar picture of the repository owner from a URL and display it on screen.
		Glide has been used over Picasso since it resizes images before storing them which takes less memory.

* **Preference library**
		For getting and storing the prefered order option choosed by the user.

* **Paging library from Jetpack**
		To be able to implement the pagination task.
		The pagination could also be done using the RecyclerView.OnScrollListener, but the paging libraries provide mode code clarity and a better lifecycle management thanks to the ViewModel. 
		