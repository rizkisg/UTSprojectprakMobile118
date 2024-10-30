package lat.pam.utsproject

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class ListFoodActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: FoodAdapter
    private lateinit var foodList: List<Food>

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_list_food)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Preparing food data
        foodList = listOf(
            Food("Nasi goreng", "Nasi goreng sedap khas Indonesia", R.drawable.nasi_goreng),
            Food("Salad", "Salad segar yang dibuat secara langsung", R.drawable.salad),
            Food("Oreo ice cream", "Ice cream dingin, lembbut, dan enak dimulut", R.drawable.icecream_circle),
            Food("Burger", "Burger lezat seperti buatan SpongeBob SquarePants", R.drawable.burger),
            Food("Donut", "Donut bulat mantap pisan", R.drawable.donut_circle),
            Food("Cake ice", "Cake ice strawbery yang segar", R.drawable.froyo_circle),
            Food("Pizza", "Pizza yang bundar ditaburi dengan berbagai toping dan keju creamy...", R.drawable.pizza),
            Food("Chiken bucket", "Paket chiken ayam yang hemat dan mantap!!!", R.drawable.chicken_bucket),
            Food("Drinks", "Minuman segar pelepas dahaga", R.drawable.drinks)
        )

        // Set up the adapter with a click listener

        adapter = FoodAdapter(foodList) { selectedFood ->
            // Start OrderActivity and pass the selected food details
            val intent = Intent(this, OrderActivity::class.java).apply {
                putExtra("FOOD_NAME", selectedFood.name)
                putExtra("FOOD_DESCRIPTION", selectedFood.description)
                putExtra("FOOD_IMAGE", selectedFood.imageResourceId) // Optional: Pass image resource ID
            }
            startActivity(intent)
        }



        recyclerView.adapter = adapter




        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}