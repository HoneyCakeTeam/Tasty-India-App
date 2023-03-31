package com.example.tastyindia.data

import com.example.tastyindia.R
import com.example.tastyindia.data.domain.Advice
import com.example.tastyindia.data.domain.HomeCategoriesModel
import com.example.tastyindia.data.domain.KitchenInfo
import com.example.tastyindia.data.domain.Recipe
import com.example.tastyindia.data.source.CsvDataSource
import kotlin.random.Random


class DataManager(dataSource: CsvDataSource) : DataManagerInterface {

    private val listOfRecipe = dataSource.getAllRecipes()

    //region Home screen
    override fun getRandomNumbersInListOfRecipe(recipeId: Int): List<Int> {
        val listOfRandomNumbers = List(30) {
            recipeId + it + 1
        }
        return listOfRandomNumbers
    }

    override fun getRecommendationFirstRecipeId(): Int = Random.nextInt(0, listOfRecipe.size - 31)

    override fun getRecipesOfWeekFirstRecipeId(): Int = Random.nextInt(0, listOfRecipe.size - 31)

    override fun getListOfRecipeUsingRandomNumbers(randomNumbers: List<Int>): List<Recipe> =
        randomNumbers.map {
            listOfRecipe[it]
        }

    override fun getListOfHomeCategories(): List<HomeCategoriesModel> =
        listOf(
            HomeCategoriesModel("Chicken", R.drawable.ic_chicken),
            HomeCategoriesModel("Meat", R.drawable.ic_meal),
            HomeCategoriesModel("Soup", R.drawable.ic_soup),
            HomeCategoriesModel("Fish", R.drawable.ic_sea_food),
            HomeCategoriesModel("Spicy", R.drawable.ic_spicy)
        )
    //endregion

    //region Kitchen screen
    override fun getKitchenInfoByName(kitchenName: String): KitchenInfo {
        return if (getKitchenInfoList().any { it.kitchenName == kitchenName }) {
            getKitchenInfoList().filter { it.kitchenName == kitchenName }[0]
        } else {
            getAnonymousKitchen()
        }
    }

    override fun getAnonymousKitchen(): KitchenInfo {
        return KitchenInfo(
            "This",
            "This cuisine dates back over 5000 years. Each region has its own traditions, religions and culture that influence its food. Hindus tend to be vegetarian and Muslims tend to have meat dishes, although pork is forbidden. Indian food has been influenced by Mongolian, Persian and Chinese cuisine, among others. The common thread throughout the centuries remains the distinct mixing of spices that invariably give Indian cuisine its flavor and aroma.",
            "Food choice varies north, south, east and west. Indians from the north eat flat breads like chapati and naan, while Southern Indians prefer to eat rice and coconut. The versatile coconut not only provides milk, it thickens stews, makes a chewy snack and is used in many sweet southern dishes as an ingredient or as a garnish. Western India is more cosmopolitan, but is known for its traditional spicy curries. Mumbai, formerly Bombay, at the heart of the region, is flooded with city dwellers, students and workers. They all have their traditional cuisines, but seafood and curries, hot and spicy sausages and snacks with chai tea are popular traditional fare. East Indian food relies heavily on rice, milk and vegetables, prepared simply with yogurt, seeds and spices steamed and curried. East Indians love their sweets and use milk and other dairy products abundantly in them.",
            "This cuisine makes best use of what is available, which is why each region has its own popular dishes. Dal, a lentil dish, is popular in the North. Meen Moli, a white fish curry, is loved in the South. Western Indians can’t get along without Vindaloo, a pork dish. East Indians love their sweets—one of the most popular being Chhenagaja—chhena, flour and sugar syrup. Halwa, a popular breakfast dish, consists of wheat, butter, sugar and almonds or pistachios. Indian snacks include samosas, a spicy turnover stuffed with potatoes and peas and a puffy rice, yogurt, tamarind and potato blend snack called bhel puri. Kabobs, meatballs, tandoori (clay-baked) chicken, rasam soup, and rice cakes, called idli, are popular dishes."
        )
    }

    override fun getAllKitchenRecipes(): List<Recipe> = listOfRecipe.distinctBy { it.cuisine }
    //endregion

    //region Category screen
    override fun getHealthyRecipes(health: List<String>): List<Recipe> {
        return listOfRecipe.filter { recipe ->
            health.any {
                recipe.ingredients.lowercase().contains(it.lowercase())
            }
        }
    }
    override fun getFastFoodRecipes(): List<Recipe> {
        return listOfRecipe.filter {
            it.totalTimeInMinutes > 0
        }.sortedBy {
            it.totalTimeInMinutes
        }
    }

    override fun getEasyRecipes(): List<Recipe> {
        return listOfRecipe.sortedBy {
            it.ingredientsCount
        }
    }

    override fun getRecipesByCategory(categoryName: String): ArrayList<Recipe> {
        return listOfRecipe.filter { recipe ->
            recipe.ingredients.lowercase().contains(categoryName.lowercase()) ||
            recipe.recipeName.lowercase().contains(categoryName.lowercase())
        } as ArrayList<Recipe>
    }

    override fun getRandomImageInCategory(): String = listOfRecipe.random().imageUrl

    //endregion

    //region Recipe Details screen
    override fun getIngredients(recipe: Recipe): List<String> = recipe.ingredients.split(";")

    override fun getInstructions(recipe: Recipe): List<String> =
        recipe.instructions.split(".").map { it.trim() }
    //endregion

    //region Search screen
    override fun searchByRecipeOrCuisine(searchWord: String): List<Recipe> {
        return listOfRecipe.filter {
            it.cuisine.lowercase().contains(searchWord.lowercase()) || it.recipeName.lowercase()
                .contains(searchWord.lowercase())
        }
    }

    //endregion

    //region kitchen details screen
    override fun getRecipesByKitchen(kitchenName: String): List<Recipe> =
        listOfRecipe.filter { it.cuisine == kitchenName }



    override fun getRecipe(id: Int) = listOfRecipe[id]

    //endregion

    //region Category static data
    override fun getHealthyIngredients(): List<String> =
        listOf(
            "Salmon",
            "Broccoli",
            "Quinoa",
            "Kale",
            "Chickpeas",
            "Blueberries",
            "Almonds",
            "Avocado",
            "Greek Yogurt",
            "Oats",
            "Chicken",
            "Fish",
            "Lentils",
            "Millet",
            "Cardamom",
            "Tomatoes",
            "Ginger",
            "Turmeric",
            "Cinnamon",
            "Sweet Potato"
        )

    //endregion

    //region Kitchen static data
    override fun getKitchenInfoList(): List<KitchenInfo> = listOf(
        KitchenInfo(
            "Indian",
            "Indian cuisine dates back over 5000 years. Each region has its own traditions, religions and culture that influence its food. Hindus tend to be vegetarian and Muslims tend to have meat dishes, although pork is forbidden. Indian food has been influenced by Mongolian, Persian and Chinese cuisine, among others. The common thread throughout the centuries remains the distinct mixing of spices that invariably give Indian cuisine its flavor and aroma.",
            "Food choice varies north, south, east and west. Indians from the north eat flat breads like chapati and naan, while Southern Indians prefer to eat rice and coconut. The versatile coconut not only provides milk, it thickens stews, makes a chewy snack and is used in many sweet southern dishes as an ingredient or as a garnish. Western India is more cosmopolitan, but is known for its traditional spicy curries. Mumbai, formerly Bombay, at the heart of the region, is flooded with city dwellers, students and workers. They all have their traditional cuisines, but seafood and curries, hot and spicy sausages and snacks with chai tea are popular traditional fare. East Indian food relies heavily on rice, milk and vegetables, prepared simply with yogurt, seeds and spices steamed and curried. East Indians love their sweets and use milk and other dairy products abundantly in them.",
            "Indian cuisine makes best use of what is available, which is why each region has its own popular dishes. Dal, a lentil dish, is popular in the North. Meen Moli, a white fish curry, is loved in the South. Western Indians can’t get along without Vindaloo, a pork dish. East Indians love their sweets—one of the most popular being Chhenagaja—chhena, flour and sugar syrup. Halwa, a popular breakfast dish, consists of wheat, butter, sugar and almonds or pistachios. Indian snacks include samosas, a spicy turnover stuffed with potatoes and peas and a puffy rice, yogurt, tamarind and potato blend snack called bhel puri. Kabobs, meatballs, tandoori (clay-baked) chicken, rasam soup, and rice cakes, called idli, are popular dishes."
        ), KitchenInfo(
            "Mexican",
            "The indigenous people of Mexico were hunter-gatherers and their diet consisted of corn, beans, chili peppers, squash, tomatoes, sweet potatoes, game, and some insects. When the New World was discovered, it changed the then-sustainable culinary landscape. If forged new ideas, new traditions, and new styles of preparing meals.\n" + "\n" + "Among the items the Spanish carried with them were citrus seeds and animals such as pigs, cows, and chickens. The discovery led to other cultural influences such as from France, Africa, and the Caribbean, each infusing their traditions with local ingredients.",
            "\n" + "Baja California – there is an abundance of seafood such as fish, shellfish, and squid. More inland is conducive for growing grapes, avocado, strawberries, mangoes, lettuce, and corn. \n" + "The North – The climate is too arid to sustain many crops but instead, they raise cattle, make cheese, beer, and grow wheat.\n" + "The Central Pacific Coast – This region has seafood, tropical fruits, rice, sugar, wheat, tequila, agave, and chiles. The town of Tequila is also home to popular hot sauce brand Cholula and this is where tequila brand José Cuervo was created. \n" + "The Central Highlands – The highlands are home to some of the largest silver mining operations in Mexico but they also focus on pork, corn, turkey, agave, garlic, onion, beans, and tomatoes.\n" + "The Gulf Coast – There is a bit of Caribbean influence in dishes and a large variety of fish, seafood, citrus, corn, tomatoes, sugar, and vanilla beans. Incidentally, this region is the largest cultivator of the cacao bean which is made into chocolate.\n" + "Southern Pacific Coast – Home to Oaxaca, they grow chiles, corn, beans, tomatoes, tomatillos, and also make a mozzarella-style cheese known as Oaxacan Cheese.\n" + "Yucatán Peninsula – This area is also known for its Caribbean influence. Here you will find plantains, habanero peppers, bitter oranges, onion, and barbequed pork called Cochinita Pibil.",
            "There are three widely used ingredients in Mexican cuisine, corn, beans, and chili peppers. All three basic ingredients are indigenous to Mexico and are inexpensive to grow. Much of the dishes you find will have some sort of corn as the ingredient such as tamales, pozole, and of course, tortillas. Tortillas were used as their utensils and they would pick food up using the tortilla and eat it, thus creating the taco and later, the burrito.\n" + "\n" + "The most popular type of beans in Mexico are the pinto bean and the black bean. Beans are used in salsas, soups, and they also make fantastic stand-alone side dishes such as refried beans, and frijoles borrachos (drunken beans).\n" + "\n" + "The Mexican word for sauce is salsa. Salsas usually include some type of chili and range from mild heat to extremely spicy. Not all food is spicy like it is in the United States, in Mexico they chose chili for their ability to deepen the flavor. Of course, that’s not to say there isn’t a heat factor to some of the cuisines."
        ), KitchenInfo(
            "Italian Recipes",
            "When you love food, there are two things you really want to do: eat it and make it. That’s why it’s nice to have a well-furnished kitchen, and plenty of interesting recipes to try, as well as a gang of good friends, to invite over to justify your spending every single weekend surrounded by pots and pans, making your best impression of a domestic goddess/god.\n" + "\n" + "But you know what, there’s something we barely stop thinking about when in the kitchen, the history behind what we’re making and eating. Have you ever thought of it? You guys, on the other side of the pond, are usually more aware of it, as your cuisine is a delicious melting pot of flavors and cultures hailing from every corner of the Earth, the heritage and history of which is usually well rooted into the community.\n" + "\n" + "In Italy, things are a bit different: we usually care deeply and lovingly about our family’s cooking history. Grandmas and moms’ recipes are passed on with care and pride, a symbol itself of one’s own heritage and roots. Some of us are more aware than others of regional characteristics typical of each dish. It is not usual though, when it comes to the kitchen, to look further back than a couple of generations. Our knowledge of why we cook in a certain way and why we eat certain things is normally based on oral sources (our elders) and therefore has a limited time span.\n" + "\n" + "The history of Italian cuisine, however, is as long and rich as the country’s history itself, its origins laying deep into the ancestral history of Rome, its people, and its political, cultural, and social power. Italian cuisine has evolved and changed following the evolution and the changes of Italy itself throughout centuries of wars, cultural mutations, and contacts: it’s a history as rich, colorful, and fascinating as the most amazing of recipes.",
            "Italy is made up of twenty regions with distinct characteristics. Every town, every village, makes the same dish in vastly different ways, and every town and village has its proudest specialty. These cooking traditions define people's identities just as much as their dialects and their traditional costumes. Local cooking preferences and customs are shaped by geographic, historical, and climactic differences: some regions are landlocked and mountainous, others hug the sea and are hilly; some regions have absorbed Arab or Greek influences, others have been marked by the French or Austrians; some regions live under the dazzling Mediterranean sun most of the year, others have cold winters, snow, fog, and harsh winds.\n" + "\n" + "Italy is a small country (less than half the size of Texas), but it is one with a long and venerable history. From the fall of the Roman Empire in 476 to 1861, when it was finally unified under one kingdom, Italy was made up of independent city states, republics, and regions that spent much of their time fighting off encroaching neighbors and outside intruders. This, along with the fact that the large-scale exchange of culinary traditions among Italians is a recent phenomenon (linked to modern roads, technology, and an improved post-war economy), explains how Italy managed to maintain its varied cuisines into the twenty-first century.",
            "It would not be an exaggeration to say that India loves its pizzas, pasta and risottos, even if it means it giving the authentic Italian dishes our own desi tadka. Italian food is one of the few global cuisines that Indians are truly obsessed with. Italian food regularly features on the dining tables of most urban Indian households, and more often than not, we fall back on pastas, pizzas and risottos to satisfy our cravings for a good meal. There are so many varieties to choose among Italian dishes in veg or non-veg, from when it comes to pasta - penne, lasagne, spaghetti, macaroni, tagliatelle and ravioli among others - that you can toss them in numerous sauces, herbs, vegetables and meats and enjoy a hearty meal. Home-made pizzas are also a favourite option for a quick meal during game nights or family get-togethers.\n" + "George Miller had rightly said, \"The trouble with eating Italian food is that two or three days later you're hungry again\". A four-course meal is served with a variety of 400 types of cheese, and every bite speaks of its origins from the 4th century BC. Did you know that Italians are known to take their food very seriously? The lunch hour is the most important meal of the day. It starts with antipasti (before the meal) like cheese, olives, salad etc. The main course mostly comprises of the most popular Italian recipe pasta or risotto. Fact: There are more than 600 shapes of pasta produced across the world."
        ), KitchenInfo(
            "Chinese",
            "History of Chinese cuisine starts with the 4 thousand year old archaeological findings of the oldest noodle food found in the upper reaches of the Chinese Yellow River. By the time of the Han Dynasty *206 BC – 220 AD), manufacture of various grain food became very organized and cooking also reflected that. Southern china was famous for their rice, and food from the North China Plain was predominately focused on flour products. Fascination with the exotic and highly specialized food started during the reign of Tang Dynasty (618–907), while tradition of drinking tea can be found from the influence of the earlier “Southern and Northern Dynasties” period of time during 5th century AD. During Tang Dynasty, tea became highly popular in elite societies, signifying wealth and balance of health. By the time of the Song Dynasty (960–1279), life in cities became more and more easier, with trade and rise of the manufacturing jobs enabled Chinese population access to better quality of life and better access to food. It was then that Chinese cuisine really blossomed, enabling mixing of cooking, medicine and even religion, establishing strict rules for maintaining “balanced” meals and expanding the ways the food can be prepared, processed and served.\n" + "\n" + "By the time of Yuan Dynasty, China received first contacts with the west, bringing for the first time access to many foreign food ingredients and methods of food preparation. This influence grew even more strongly during Ming dynasty (1368–1644) when trading with the rest of the world became much easier with the establishment of sea trading roots. By then, china gained access to main new plants, animal, food crops, and goods, including the food that was originally found only in the newly discovered “New World” (sweet potatoes, peanuts, maize and many others).\n" + "\n" + "In recent history, founding of People's Republic of China brought several changes of the cuisine that were both fueled by government efforts and created by minorities and western influence. In general, modern Chinese cuisine can be separated using two different schools of food. “Four Schools” refer to the cooking traditions of Shandong, Su, Cnatonese and Sitchuan, while the four additional cuisines developed in the territories of Hunan, Fujian, Anhui and Zhejiang. They all produce incredible variety of food based on rice, noodles, wheat, soybeans, herbs, seasonings and vegetables.",
            "Anhui (Hui)\n" + "Anhui cuisine is derived from the native cooking styles of the people located in the Huangshan Mountains region in China. Although it is similar to Jiangsu cuisine, there is less emphasis on seafood and more on a wide variety of locally grown herbs and vegetables from both the land and the sea.\n" + "\n" + "Cantonese (Yu)\n" + "Due to Guangdong’s proximity to the South China Sea, the people of the region have access to a plentiful supply of imported food and fresh seafood. Cantonese cuisine incorporates almost all edible meats, including chicken feet, duck’s tongue, snakes and snails. However, due to availability, lamb and goat are rarely eaten. Many cooking techniques are used, including wok hei (stir frying) and steaming. Spices are used moderately, and fresh herbs are seldom added to the food. Dishes include dim sum, small morsels of food typically served at breakfast or lunch alongside tea; barbequed char siu, sticky and burnt red in color; and clear broths flavored with meat stock.\n" + "\n" + "Fujian (Min)\n" + "Fujian cuisine is influenced by its coastal position and mountainous terrain, and ingredients such as woodland mushrooms, bamboo shoots, fish, shellfish and turtles are used regularly. The cuisine in this area is known to have particular emphasis on umami taste; the dishes are notoriously light and flavorful.\n" + "\n" + "Hunan (Xiang)\n" + "Like Szechuan cuisine, Hunan food is renowned for being hot and spicy, with garlic, chili peppers and shallots used liberally. However, unlike Szechuan cuisine, it is known for being purely hot as opposed to the searing, numbing heat of Szechuan cooking.\n" + "\n" + "Jiangsu (Su)\n" + "Jiangsu cuisine consists of a several different styles of Chinese cooking, namely Huaiyang, Yangzhou, Nanjing, Suzhou and Zhenjiang. The food in Jiangsu cuisine is known as being soft, but not to the point of falling apart: the meat tastes tender but wouldn’t separate from the bone when picked up.\n" + "\n" + "Shandong (Lu)\n" + "Derived from the native cooking styles of Shandong, a northern coastal province of China, Shandong cuisine consists of two predominant styles: Jiaodong, characterized by light seafood dishes; and Jinan, a style that features the use of soup in its dishes. Although it is less available in the West, Shandong cuisine is often considered one of the most influential styles of cooking in the Chinese culinary history.\n" + "\n" + "Szechuan (Chuan)\n" + "Szechuan cuisine is renowned for its use of bold flavors; chili, garlic and Szechuan pepper are used liberally throughout the dishes. Szechuan pepper has a unique taste: it is intensely fragrant, citrusy and causes a numbing sensation in the mouth. Szechuan cuisine often contains food that has been preserved through pickling, salting and drying.\n" + "\n" + "Zhejiang (Zhe)\n" + "In general, Zhejiang food is fresh and light rather than greasy. It consists of at least four styles of cooking: Hangzhou, characterized by the use of rich foods and bamboo shoots; Shaoxing, specializing in poultry and fish; Ningbo, specializing in seafood; and Shanghai, with xiao long bao.",
            "In a country where the traditional way to greet someone translates to 'have you eaten yet?' (ni chile ma), be rest assured, the food will be extraordinary. China has the most popular culinary heritage in the world. The history of their cuisine dates back to about 1000 years with varied cooking styles, techniques and ingredients that have evolved over time.\n" + "A typical Chinese meal will have two things - a carbohydrate or starch like noodles, rice or buns, and accompanying stir fries or dishes of veggies, fish and meat. They use a lot of fresh vegetables like mushroom, water chestnuts, bamboo and even tofu. In North China, wheat-based accompaniments like noodles and steamed buns dominate the table, in contrast to South China where rice is a favourite. The short-grain sticky rice, grown throughout Southern China, is absolutely irresistible.\n" + "Each dish focuses on creating a balance between three aspects - appearance, aroma, and taste. They pay a lot of attention to the aesthetic appearance of the food with diversified colours. Sauces and seasonings like fish sauce, five spice powder, oyster sauce, soy sauce, vinegar, root garlic, fresh ginger and others are used generously to offer a complex play of flavour and aroma.\n" + "Much like Japanese cuisine, Chinese dishes are rich in umami which is described as a 'pleasant savory taste'. The umami taste is common to many ingredients used in their cuisine like Chinese cabbage, spinach, celery, green tea or fermented products like soy sauce and pastes.\n" + "Chinese food and the way it is prepared is influenced by the two major philosophies - Confucianism and Taoism. One of the standards set by Confucius was that food must be cut into small bite- size pieces before being served. Those who follow Taoism focus more on food that promote health and longevity and those that have healing powers."
        ), KitchenInfo(
            "European",
            "We present a summary of the history of gastronomy in Europe, which appears very different to the subject of European food. In fact, throughout history, the daily cookery of ordinary people is simpler and less varied than that of the rich. Here, we discuss the cuisine of the upper classes as it appears in the cookbooks of the time. These historical cookbooks present us with a cuisine that is roughly equivalent to that of the three-star Michelin restaurants of today.\n" + "\n" + "We grouped periods accordingly: Medieval cuisine and Renaissance cuisine share similar characteristics, specifically in their use of spices; the cuisine of the 17th and 18th century shows a clear change from that past, where the French Revolution marks an important separation before the development of the cuisine of the bourgeoisie and the aristocracy of the 19th century.",
            "A continent with some of the best-known and renowned cuisine in the world, Europe has had thousands of years to experiment, adapt, and perfect its cooking.\n" + "\n" + "With various landscapes, ranging from the cold regions of Norway and Finland to the warm Mediterranean area containing Greece and Italy, Europe’s cuisine differs significantly while staying true to its roots and native-grown foods.\n" + "\n" + "As Europe faced and changed throughout formative periods such as the Romans, Ottomans, Moors, Middle Ages, Renaissance, and the bourgeoisie in the 17th and 18th centuries, these cuisines have adapted, and transformed into what we know today.\n" + "\n" + "“I am more of an herb guy than a spice guy. It comes back to a certain conservatism I have regarding food. The French are not big on spices; they use more herbs. I know the spices used in European cooking and use them in moderation. I am not going to serve a dish that is wildly nutmegged!”\n" + "\n" + "— David Waltuck, Chanterelle NYC",
            "If you’ve fantasized about eating your way through Europe, you’re not alone. From rich stews and dumplings in the Balkans, to seafood in the Mediterranean, to (of course) savory cheeses throughout, the continent is a foodie’s dream.\n" + "\n" + "With each country, there is one dish that stands out as the most recognizable, most famed and most sought-after by visitors: the food staple that people think of when they think of the place. These traditional foods are not only delectable, but they also tell the story of a country’s history, people and traditions.\n" + "\n" + "Join us as we travel through the gastronomical scenes of Europe and Eurasia, one country and iconic food at a time. Fair warning: This article is best read when not on an empty stomach."
        )
    )

    //endregion

    //region Advices screen
    override fun getAdvicesList(): List<Advice> = listOf(
        Advice(
            adviceImageUrl = "https://experiencelife.lifetime.life/wp-content/uploads/2003/11/good-carbs-bad-carbs-1144977994-1280x720.jpg",
            adviceDescription = "Choose good carbs, not no carbs. Whole grains are your best bet."
        ),
        Advice(
            adviceImageUrl = "https://www.washingtonpost.com/wp-apps/imrs.php?src=https://arc-anglerfish-washpost-prod-washpost.s3.amazonaws.com/public/4G5HMDUYGMI6TGQW3RKR5JNEHM.jpg&w=1440",
            adviceDescription = "Pay attention to the protein package. Fish, poultry, nuts, and beans are the best choices."
        ),
        Advice(
            adviceImageUrl = "https://hips.hearstapps.com/hmg-prod/images/food-with-high-content-of-healthy-fats-overhead-royalty-free-image-1649362992.jpg",
            adviceDescription = "Choose foods with healthy fats, limit foods high in saturated fat, and avoid foods with trans fat."
        ),
        Advice(
            adviceImageUrl = "https://www.healthifyme.com/blog/wp-content/uploads/2019/11/High-fiber-PCOS-1-500x375.jpg",
            adviceDescription = "Choose a fiber-filled diet, rich in whole grains, vegetables, and fruits."
        ),
        Advice(
            adviceImageUrl = "https://post.healthline.com/wp-content/uploads/2020/08/fruits-and-vegetables-thumb-1.jpg",
            adviceDescription = "Eat more vegetables and fruits. Go for color and variety—dark green, yellow, orange, and red."
        ),
        Advice(
            adviceImageUrl = "https://m.economictimes.com/thumb/height-450,width-600,imgsize-354532,msid-64272575/not-just-for-bones-calcium-is-crucial-for-cardiac-functioning-too-heres-how-to-ensure-youre-never-deficient.jpg",
            adviceDescription = "Calcium is important. But milk isn’t the only, or even best, source."
        ),
        Advice(
            adviceImageUrl = "data:image/jpeg;base64,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",
            adviceDescription = "Water is best to quench your thirst. Skip the sugary drinks, and go easy on the milk and juice."
        ),
        Advice(
            adviceImageUrl = "https://c.ndtvimg.com/2020-01/rbm7eojo_salt-_625x300_11_January_20.jpg",
            adviceDescription = "Eating less salt is good for everyone’s health. Choose more fresh foods and fewer processed foods."
        ),
        Advice(
            adviceImageUrl = "https://images.healthshots.com/healthshots/en/uploads/2022/03/28211325/Vitamin-d-foods.jpg",
            adviceDescription = "A daily multivitamin is a great nutrition insurance policy. Some extra vitamin D may add an extra health boost."
        )
    )
    //endregion



}