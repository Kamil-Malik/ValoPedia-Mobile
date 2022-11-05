package com.lelestacia.valorantgamepedia.ui.agents.agents_detail

import android.os.Build
import android.os.Bundle
import android.window.OnBackInvokedDispatcher
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lelestacia.valorantgamepedia.data.model.remote.agent_data.AgentData
import com.lelestacia.valorantgamepedia.databinding.ActivityAgentsDetailBinding

class AgentsDetailActivity : AppCompatActivity() {

    private var _binding: ActivityAgentsDetailBinding? = null
    private val binding get() = _binding!!

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityAgentsDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data =
            if (Build.VERSION.SDK_INT >= 33) {
                intent.getParcelableExtra("AGENT", AgentData::class.java) as AgentData
            } else {
                intent.getParcelableExtra<AgentData>("AGENT") as AgentData
            }
        val adapter = AgentSkillAdapter()

        supportActionBar?.hide()

        binding.apply {
            rvAgentSkill.adapter = adapter
            rvAgentSkill.layoutManager =
                LinearLayoutManager(this@AgentsDetailActivity, RecyclerView.HORIZONTAL, false)
            tvAgentName.text = data.displayName
            tvAgentDescription.text = data.description
            tvAgentRoleTitle.text = data.role?.displayName
            tvAgentRoleDescription.text = data.role?.description

            Glide.with(this@AgentsDetailActivity)
                .load(data.role?.displayIcon)
                .fitCenter()
                .into(ivAgentRoleIcon)

            Glide.with(this@AgentsDetailActivity)
                .load(data.fullPortrait)
                .fitCenter()
                .into(ivAgentPhoto)
        }

        adapter.submitList(data.abilities)
    }

    override fun getOnBackInvokedDispatcher(): OnBackInvokedDispatcher {
        finish()
        return super.getOnBackInvokedDispatcher()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}