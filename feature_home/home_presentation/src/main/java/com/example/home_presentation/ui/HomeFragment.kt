package com.example.home_presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.home_presentation.databinding.FragmentHomeBinding
import com.example.home_presentation.ui.adapter.PatientAdapter
import com.example.home_presentation.viewmodel.HomeViewModel
import com.example.navigation.Navigator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {
    /*@Inject
    lateinit var navigator: Navigator*/

    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val homeViewModel : HomeViewModel by viewModels()
    private lateinit var adapter : PatientAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //homeViewModel.getPatient()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        shimmerEffectPlaceholder()
        setOnClickListenerAddPatient()
        setPatientRecyclerView()
        //setOnClickListenerPatient()
    }

    private fun shimmerEffectPlaceholder() {
        binding.shimmerLayout.startShimmer()
    }

    /*private fun setOnClickListenerPatient() {
        adapter.onPatientClickListener = {
            val transaction = activity?.supportFragmentManager?.beginTransaction()
            transaction?.replace(
                R.id.main_layout,
                newInstancePatientId(id = it.id.toString())
            )?.addToBackStack(null)
            transaction?.commit()
        }
    }*/

    private fun setPatientRecyclerView() {
        adapter =  PatientAdapter()
        /*val bottomSpaceItemDecoration =
            BottomSpaceItemDecoration(resources.getDimensionPixelSize(R.dimen.bottom_space))*/
        homeViewModel.patients.observe(viewLifecycleOwner, Observer { patient ->
            adapter.submitList(patient)
            binding.shimmerLayout.stopShimmer()
            binding.shimmerLayout.visibility = View.GONE
            binding.rvPatients.visibility = View.VISIBLE

        })
        binding.rvPatients.adapter = adapter
        //binding.rvPatients.addItemDecoration(bottomSpaceItemDecoration)
    }

    private fun setOnClickListenerAddPatient() {
        binding.btAddPatient.setOnClickListener {
            //replaceFragmentMain(AddPatientFragment())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.rvPatients.adapter = null
        _binding = null
    }

    override fun onPause() {
        super.onPause()
        binding.shimmerLayout.stopShimmer()
    }

    override fun onResume() {
        super.onResume()
        binding.shimmerLayout.startShimmer()
    }

    companion object {
        private const val BUNDLE_PATIENT_ID = "patient_id"

        /*fun newInstancePatientId(id : String) =
            DetailedInformationFragment().apply {
                arguments = Bundle().apply {
                    putString(BUNDLE_PATIENT_ID, id)
                }
            }*/


    }
}