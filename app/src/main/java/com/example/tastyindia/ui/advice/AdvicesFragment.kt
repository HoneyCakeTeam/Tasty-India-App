package com.example.tastyindia.ui.advice

import com.example.tastyindia.data.domain.Advice
import com.example.tastyindia.databinding.FragmentAdvicesBinding
import com.example.tastyindia.ui.BaseFragment
import com.example.tastyindia.ui.adviceAdabter

class AdvicesFragment : BaseFragment<FragmentAdvicesBinding>() {
    override val TAG: String = "AdvicesFragment"
    private val advices: MutableList<Advice> = mutableListOf()

    override fun getViewBinding(): FragmentAdvicesBinding =
        FragmentAdvicesBinding.inflate(layoutInflater)

    override fun setUp() {
        addItems()
        val adabter = adviceAdabter(advices)
        binding.rvAdvices.adapter = adabter
    }

    override fun addCallbacks() {

    }

    private fun addItems() {
        advices.add(
            makeAdvice(
                url = "https://experiencelife.lifetime.life/wp-content/uploads/2003/11/good-carbs-bad-carbs-1144977994-1280x720.jpg",
                text = "Choose good carbs, not no carbs. Whole grains are your best bet"
            )
        )
        advices.add(
            makeAdvice(
                url = "https://www.washingtonpost.com/wp-apps/imrs.php?src=https://arc-anglerfish-washpost-prod-washpost.s3.amazonaws.com/public/4G5HMDUYGMI6TGQW3RKR5JNEHM.jpg&w=1440",
                text ="Pay attention to the protein package. Fish, poultry, nuts, and beans are the best choices"
            )
        )
        advices.add(
            makeAdvice(
                url = "https://hips.hearstapps.com/hmg-prod/images/food-with-high-content-of-healthy-fats-overhead-royalty-free-image-1649362992.jpg",
                text ="Choose foods with healthy fats, limit foods high in saturated fat, and avoid foods with trans fat. Plant oils, nuts, and fish are the healthiest sources"
            )
        )
        advices.add(
            makeAdvice(
                url = "https://www.healthifyme.com/blog/wp-content/uploads/2019/11/High-fiber-PCOS-1-500x375.jpg",
                text ="Choose a fiber-filled diet, rich in whole grains, vegetables, and fruits"
            )
        )
        advices.add(
            makeAdvice(
                url = "https://post.healthline.com/wp-content/uploads/2020/08/fruits-and-vegetables-thumb-1.jpg",
                text ="Eat more vegetables and fruits. Go for color and variety—dark green, yellow, orange, and red"
            )
        )
        advices.add(
            makeAdvice(
                url = "https://m.economictimes.com/thumb/height-450,width-600,imgsize-354532,msid-64272575/not-just-for-bones-calcium-is-crucial-for-cardiac-functioning-too-heres-how-to-ensure-youre-never-deficient.jpg",
                text ="Calcium is important. But milk isn’t the only, or even best, source"
            )
        )
        advices.add(
            makeAdvice(
                url = "data:image/jpeg;base64,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",
                text ="Water is best to quench your thirst. Skip the sugary drinks, and go easy on the milk and juice"
            )
        )
        advices.add(
            makeAdvice(
                url = "https://c.ndtvimg.com/2020-01/rbm7eojo_salt-_625x300_11_January_20.jpg",
                text ="Eating less salt is good for everyone’s health. Choose more fresh foods and fewer processed foods"
            )
        )
        advices.add(
            makeAdvice(
                url ="https://images.healthshots.com/healthshots/en/uploads/2022/03/28211325/Vitamin-d-foods.jpg",
                text ="A daily multivitamin is a great nutrition insurance policy. Some extra vitamin D may add an extra health boost"
            )
        )

    }

    private fun makeAdvice(url: String, text: String) = Advice(url, text)


}