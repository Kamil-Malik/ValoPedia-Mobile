package com.lelestacia.valorantgamepedia.ui.activity

import android.R.color
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.lelestacia.valorantgamepedia.databinding.ActivityAgentsDetailBinding
import com.lelestacia.valorantgamepedia.ui.adapter.AgentSkillAdapter
import com.lelestacia.valorantgamepedia.ui.adapter.AgentTagAdapter
import com.lelestacia.valorantgamepedia.ui.adapter.AgentsAdapter
import com.lelestacia.valorantgamepedia.ui.viewmodel.AgentDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AgentsDetailActivity : AppCompatActivity() {

    private var _binding: ActivityAgentsDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityAgentsDetailBinding.inflate(layoutInflater)
        supportActionBar?.hide()
        window.statusBarColor = ContextCompat.getColor(this@AgentsDetailActivity, color.black)
        setContentView(binding.root)

       lifecycleScope.launchWhenCreated {
           val viewModel by viewModels<AgentDetailViewModel>()
           val key = intent.getStringExtra(AgentsAdapter.AGENT_UUID) ?: ""
           val data = viewModel.getAgents(key)
           val agentSkillAdapter = AgentSkillAdapter()
           binding.apply {
               rvAgentSkill.setHasFixedSize(true)
               rvAgentSkill.adapter = agentSkillAdapter
               rvAgentSkill.layoutManager =
                   LinearLayoutManager(this@AgentsDetailActivity, RecyclerView.HORIZONTAL, false)
               tvAgentName.text = data.agent.displayName
               tvAgentDescription.text = data.agent.description
               tvAgentRoleTitle.text = data.agent.role.displayName
               tvAgentRoleDescription.text = data.agent.role.roleDescription

               Glide.with(this@AgentsDetailActivity)
                   .load(data.agent.role.displayIcon)
                   .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                   .fitCenter()
                   .into(ivAgentRoleIcon)

               Glide.with(this@AgentsDetailActivity)
                   .load(data.agent.displayPortrait)
                   .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                   .fitCenter()
                   .into(ivAgentPhoto)

               if (data.agent.displayTag.isEmpty()) {
                   tvHeaderTag.visibility = View.GONE
                   rvAgentTag.visibility = View.GONE
               } else {
                   val tagAdapter = AgentTagAdapter()
                   spacerTag.visibility = View.GONE
                   rvAgentTag.adapter = tagAdapter
                   rvAgentTag.layoutManager = object :
                       LinearLayoutManager(this@AgentsDetailActivity, RecyclerView.HORIZONTAL, false) {
                       override fun canScrollHorizontally(): Boolean {
                           return false
                       }
                   }
                   rvAgentTag.setHasFixedSize(true)
                   tagAdapter.submitList(data.agent.displayTag)
               }

               agentSkillAdapter.submitList(data.ability)
           }
       }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}