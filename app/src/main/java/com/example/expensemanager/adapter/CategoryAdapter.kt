import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.expensemanager.databinding.SampleCategoryItemBinding
import com.example.expensemanager.model.Category

class CategoryAdapter(private val onItemClick: (Category) -> Unit) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {
    var categoriesList = ArrayList<Category>()

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

        // Set click listener for the category item
        holder.itemView.setOnClickListener {
            onItemClick(category)
        }
    }
}
