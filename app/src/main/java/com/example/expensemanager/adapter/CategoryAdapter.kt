import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.expensemanager.databinding.SampleCategoryItemBinding
import com.example.expensemanager.model.Category


class CategoryAdapter(private val onItemClick: (Category) -> Unit) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {
    var categoriesList = ArrayList<Category>()
    private lateinit var contex:Context

    inner class CategoryViewHolder(val binding: SampleCategoryItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            SampleCategoryItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return categoriesList.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category: Category = categoriesList[position]
        holder.binding.categoryText.text = category.categoryName
        holder.binding.categoryIcon.setImageResource(category.categoryImage)
        Log.d("test",category.categoryColor.toString())
        holder.binding.categoryIcon.backgroundTintList = ColorStateList.valueOf(Color.parseColor(category.categoryColor))
       // holder.binding.categoryIcon.backgroundTintList = ColorStateList.valueOf(category.categoryColor)

        // Set click listener for the category item
        holder.itemView.setOnClickListener {
            onItemClick(category)
        }
    }
}
