package layout

import com.example.tastyindia.data.domain.Recipe

interface CatecoryInteractorListener {
        fun onClickItem(recipe: Recipe)
}