package kz.kuz.menu

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class MainFragment : Fragment() {
    private var mSubtitleVisible = false
    var items_number = 5

    // методы фрагмента должны быть открытыми
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        activity?.setTitle(R.string.toolbar_title)
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        if (savedInstanceState != null) {
            mSubtitleVisible = savedInstanceState.getBoolean("subtitle")
        }
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.fragment_main, menu)
        val subtitleItem = menu.findItem(R.id.subtitle)
        if (mSubtitleVisible) {
            subtitleItem.title = "Hide Subtitle"
        } else {
            subtitleItem.title = "Show Subtitle"
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.subtitle -> {
                mSubtitleVisible = !mSubtitleVisible
                updateSubtitle()
                true
            }
            R.id.item2 -> true
            R.id.item3 -> true
            R.id.item4 -> true
            R.id.item5 -> true
            R.id.item6 -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onResume() {
        super.onResume()
        updateSubtitle()
        // необходим для обновления числа в подзаголовке при возвращении к активности
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean("subtitle", mSubtitleVisible)
        // для того, чтобы подзаголовок воссоздавался после поворота экрана
    }

    private fun updateSubtitle() {
        var subtitle: String? = getString(R.string.subtitle_format, items_number)
        activity?.invalidateOptionsMenu()
        // необходим для смены надписи "Show Subtitle" на "Hide Subtitle" и наоборот
        if (!mSubtitleVisible) {
            subtitle = null
        }
        val activity = activity as AppCompatActivity?
        activity?.supportActionBar?.subtitle = subtitle
    }
}