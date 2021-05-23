package com.example.project.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project.R
import com.example.project.presentation.api.GotAPI
import com.example.project.presentation.api.GOTResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class CharacterListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView

    private val adapter = CharacterAdapter(listOf(), ::onClickedCharacter)
    private val layoutManager = LinearLayoutManager(context)

    private fun onClickedCharacter(character : Character){
        findNavController().navigate(R.id.navigateToCharacterListFragment)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_got_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.got_recyclerview)

        recyclerView.apply {
            layoutManager = this@CharacterListFragment.layoutManager
            adapter = this@CharacterListFragment.adapter
        }

        val retrofit = Retrofit.Builder()
            .baseUrl("https://anapioficeandfire.com/api/characters/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val gotAPI: GotAPI = retrofit.create(GotAPI::class.java)

        gotAPI.getCharacterList().enqueue(object: Callback<GOTResponse> {
            override fun onFailure(call: Call<GOTResponse>, t:Throwable){

            }
            override fun onResponse(call: Call<GOTResponse>, response: Response<GOTResponse>){
                if(response.isSuccessful && response.body() != null) {
                    val gotResponse = response.body()!!
                    adapter.updateList(gotResponse.results)
                }
            }
        })

        val gotList = arrayListOf<Character>().apply {
            add(com.example.project.presentation.list.Character("Jon Snow"))
            add(com.example.project.presentation.list.Character("Sansa Stark"))
            add(com.example.project.presentation.list.Character("Bran Stark"))
            add(com.example.project.presentation.list.Character("Eddard Stark"))
            add(com.example.project.presentation.list.Character("Arya Stark"))

        }

        adapter.updateList(gotList)
    }
}
