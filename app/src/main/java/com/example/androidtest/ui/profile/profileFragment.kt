package com.example.androidtest.ui.profile

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.androidtest.Adapters.ImageSliderAdapter
import com.example.androidtest.Adapters.PopularAgapter
import com.example.androidtest.Models.PopularModel
import com.example.androidtest.Models.SharedModel
import com.example.androidtest.R
import com.example.androidtest.ui.MenuBottomSheer
//import com.google.firebase.database.DatabaseReference


class profileFragment : Fragment() {

private lateinit var viewPager2: ViewPager2
private lateinit var adapter: ImageSliderAdapter
private lateinit var imageList: ArrayList<Int>
private lateinit var handler: Handler

private lateinit var popularAgapter: PopularAgapter
private lateinit var listPopular: ArrayList<PopularModel>
private lateinit var homeRv: RecyclerView
//private lateinit var mGoods: DatabaseReference

private lateinit var goMenuText: TextView

private lateinit var sharedModel: SharedModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
            val view = inflater.inflate(R.layout.fragment_profile, container, false)
            viewPager2 = view.findViewById(R.id.imageSlider)

        sharedModel = ViewModelProvider(requireActivity()).get(SharedModel::class.java)

        homeRv = view.findViewById(R.id.home_RV)

        goMenuText = view.findViewById(R.id.go_menu)
        goMenuText.setOnClickListener {
            val bottomSheetMenu = MenuBottomSheer()
            bottomSheetMenu.show(parentFragmentManager,"Test")

        }

        listPopular = ArrayList()
           /* var db = Firebase.firestore
            db.collection("Goods")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    listPopular.add(PopularModel(
                        document.getString("photo"),
                        document.getString("name"),
                        document.getString("price")
                    ))
                }
            }*/
        listPopular.add(PopularModel(R.drawable.banner_1, "Iphone 15 Pro Max Gold", "1499$",1))
        listPopular.add(PopularModel(R.drawable.banner_2, "Iphone 15 Pro Max White", "1399$",1))
        listPopular.add(PopularModel(R.drawable.banner_3, "Iphone 15 Pro Max Black", "1399$",1))
        listPopular.add(PopularModel(R.drawable.banner_4, "Iphone 15 Pro Blue", "1299$",1))
        listPopular.add(PopularModel(R.drawable.banner_5, "Iphone 14 Yellow", "999$",1))
        listPopular.add(PopularModel(R.drawable.banner_6, "Samsung Galaxy S24 Ultra Black", "1099$",1))
        listPopular.add(PopularModel(R.drawable.banner_7, "Samsung Galaxy S24 Black", "799$",1))
        listPopular.add(PopularModel(R.drawable.banner_8, "MacBook Air 13 Midnight Black", "1649$",1))
        listPopular.add(PopularModel(R.drawable.banner_9, "iPad Pro 2024 Black", "2349$",1))
        listPopular.add(PopularModel(R.drawable.banner_10, "Apple Watch Series 9 Midnight Black", "599$",1))
        listPopular.add(PopularModel(R.drawable.banner_11, "Apple AirPods Pro 2", "299$",1))


            popularAgapter = PopularAgapter(requireContext(), listPopular)
            popularAgapter.setSharedModel(sharedModel)

            homeRv.layoutManager = GridLayoutManager(requireContext(), 2)
            homeRv.adapter = popularAgapter

            return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        setTransformer()
        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                viewPager2.removeCallbacks(runnable)
                viewPager2.postDelayed(runnable, 4000)
            }

        })

    }

    private val runnable = Runnable {
        viewPager2.currentItem = viewPager2.currentItem + 1
    }

    private fun setTransformer() {

        val transformer = CompositePageTransformer()
        transformer.addTransformer(MarginPageTransformer(10))
        transformer.addTransformer{page, position->
            val r = 1 -Math.abs(position)
            page.scaleY = 0.85f + r * 0.14f
        }

        viewPager2.setPageTransformer(transformer)

    }

    override fun onPause() {
        super.onPause()
        viewPager2.removeCallbacks(runnable)
    }

    override fun onStart() {
        super.onStart()
        viewPager2.postDelayed(runnable, 4000)
    }

    private fun init() {

        imageList = ArrayList()
        adapter = ImageSliderAdapter(requireContext(), imageList, viewPager2)
        handler = Handler(Looper.myLooper()!!)

        imageList.add(R.drawable.banner_c)
        imageList.add(R.drawable.banner_a)
        imageList.add(R.drawable.banner_e)
        imageList.add(R.drawable.banner_b)
        imageList.add(R.drawable.banner_d)

        viewPager2.adapter = adapter
        viewPager2.offscreenPageLimit = 3
        viewPager2.clipToPadding = false
        viewPager2.clipChildren = false
        viewPager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

    }
}