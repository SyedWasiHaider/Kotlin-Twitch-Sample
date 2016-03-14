# Kotlin-Twitch-Sample
A project playing with Kotlin on Android and using the Twitch and GiantBomb API

Main Technical Highlights
* Kotlin extensions
* Kotlin Lambdas
* Android Staggered RecyclerView
* Pagination or "Loading more as you approach the bottom"
* Android Shared Element Transitions
* Some libraries:
  * Picasso
  * kittinunf.fuel (HTTP library)
  * Kotson (Json de/serialization)

The good
* Recyclerview!
  
The bad
* Not really using any DI (kind of hard with Kotlin -- not impossible though)
* API design is yucky
* Detail page needs more

The ugly
* Started over a weekend while I was bored so code quality was not exactly top priority
* Using a weird hack for the "loading more" feature on the recyclerview

