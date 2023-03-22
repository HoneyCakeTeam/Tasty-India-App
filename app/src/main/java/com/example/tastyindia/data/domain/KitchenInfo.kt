package com.example.tastyindia.data.domain

import com.example.tastyindia.R

data class KitchenInfo(
    var kitchenName: String,
    var kitchenImage: Int,
    var kitchenDescription: String,
    var RegionsDescription: String,
    var dishesDescription: String
)

val indianKitchen = KitchenInfo(
    "Indian Cuisine",
    R.drawable.card_image,
    "The people of early Vedic civilizations in India were nomadic pastoralists who practiced elaborate rituals to placate their gods. Agni, the god of fire, was considered the mouth through which the gods ate their sacrifices, and one of his favourite foods was ghee (clarified butter), which remains popular to this day. Animal sacrifices were common, and the meat was then consumed by those participating in the ritual. While some textual evidence suggests that these sacrifices included cattle, some argue that this claim is a result of mistranslation. (The subject of eating beef is politically contentious in present-day India.) Barley was common during the period, and there is no mention of wheat. Milk and its products, such as yogurt and barley-based milk porridges, are documented.\n" +
            "Rapid development of agriculture in the first millennium BCE brought urbanization to northern India. The word ahimsa (“nonviolence,” or “noninjury,” in Sanskrit) is first seen in the sacred texts called the Upanishads. The rise of Buddhism and Jainism, which preach compassion to all living things, encouraged vegetarianism. Jainism decrees that foods that inflame the passions, including onion and garlic, must be avoided, and foods that grow underground should also be shunned because eating them involves killing microorganisms. The Sanskrit grammarian Panini, in his work Ashtadhyayi (6th–5th century BCE), describes three categories of food: meat, lentil-based soup, and vegetables. Chanakya’s Arthasastra, a manual on how to run a kingdom that may date in part to the 4th century BCE, elaborately describes how certain foods are to be prepared, most of them with spices. Ayurveda, a traditional system of Indian medicine, describes foods as sattvic (pure or balanced), rajasic (active or increasing the energy of the body), and tamasic (heavy or reducing the energy of the body).\n" +
            "In the first millennium CE, the caste system became widespread in India, and many guidelines about how to eat food evolved based on a person’s caste. Lists of forbidden foods from this period include onion, garlic, mushrooms, meat not obtained by hunting, and alcohol. Sharing foods with people from other castes was considered taboo.",
    "The cuisine of northern India shows a stronger influence of the Islamic conquest. Dairy products such as milk, ghee, and paneer (cottage cheese) are commonly used, and many vegetables are cooked in yogurt or onion-and-tomato–based gravies. A griddle is used to make flatbread such as roti, while a tandoor (a cylindrical coal-fired oven) is used to cook flatbreads such as naan and kulcha. Puri and similar breads are deep-fried in oil, usually groundnut oil. Flatbread is typically eaten with cooked lentils (dal) and vegetables. The nonvegetarian cuisine of kebabs and pilaf is very similar to what is found in Pakistan. A popular snack is the samosa, which is cooked potato stuffed in flour and then deep-fried in oil. Street foods such as kachori and chaat are very popular, as well as sweets (called mithai) such as gulab jamun, petha, and rasmalai. Food of this region is typically richly spiced. Variations exist within northern India, from the desert regions of Rajasthan, which see heavy use of gram flour in dishes, to the vales of Kashmir, where ingredients such as mutton and dry fruits are used in dishes typically served in a wazwan, or multicourse meal.\n" +
            "\n" +
            "To the east, in West Bengal and Odisha, the amount of spice used in cooking decreases, and mustard oil is more common. The cuisine of northeastern India is rice-based—rice is grown on terraced fields in the region’s hilly terrain—and freshwater fish appears in many dishes, as does pork, beef, mutton, and chicken. Poppy seeds are also frequently used. This region’s cuisine is very similar to that of neighbouring Bangladesh.\n" +
            "\n" +
            "In southern India rice is the staple food, and it is eaten with sambhar (sambar), a watery stew comprising lentil, tamarind, and vegetables. Many fruits and vegetables are pickled and consumed with meals, and coconut oil and gingelly (sesame) oils are used as the mediums for cooking. Seafood is common along the coast. Within southern India there are diverse cuisines—Andhra, Tamil, Chettinad, Kerala, and Mangalore, among others. Each region cooks sambhar differently and uses different varieties of rice. Tamil cuisine classifies food into six tastes—sweet, sour, salty, bitter, pungent, and astringent—and aspires to include each taste in every main meal. Tamil meals are also typically served on banana leaves.\n" +
            "\n" +
            "The west coast of India has distinct cuisines as well. In Goa rice and fish are the staples. Goan Hindu cuisine is less spicy and includes many vegetables, though little onion and garlic. Portuguese cuisine in Goa includes beef and uses a vinegar-based gravy (vindaloo) for many dishes. In coastal Maharashtra fish and rice are also common, while millets and groundnuts are used in inland areas instead of rice and coconut. Further north, Gujarat is predominantly vegetarian, and most dishes have some sweetness due to the use of sugar. Roti, dal, and vegetables are common there.\n" +
            "\n" +
            "Food is typically eaten by hand across India, with minimal use of cutlery. When eating roti, a person tears off a portion of the flatbread and uses it to scoop the dal or vegetable.",
    "curry, (from Tamil kari: “sauce”), in Western usage, a dish composed with a sauce or gravy seasoned with a mixture of ground spices that is thought to have originated in India and has since spread to many regions of the world."
)
