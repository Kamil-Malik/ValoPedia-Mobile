package com.lelestacia.valorantgamepedia.ui.agents.agents_detail

import android.R.color
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.lelestacia.valorantgamepedia.data.model.remote.agent_data.RemoteAgentData
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
                intent.getParcelableExtra("AGENT", RemoteAgentData::class.java) as RemoteAgentData
            } else {
                intent.getParcelableExtra<RemoteAgentData>("AGENT") as RemoteAgentData
            }
        val agentSkillAdapter = AgentSkillAdapter()

        supportActionBar?.hide()
        window.statusBarColor = ContextCompat.getColor(this, color.black)

        binding.apply {
            rvAgentSkill.setHasFixedSize(true)
            rvAgentSkill.adapter = agentSkillAdapter
            rvAgentSkill.layoutManager =
                LinearLayoutManager(this@AgentsDetailActivity, RecyclerView.HORIZONTAL, false)
            tvAgentName.text = data.displayName
            tvAgentDescription.text = data.description
            tvAgentRoleTitle.text = data.role?.displayName
            tvAgentRoleDescription.text = data.role?.description

            Glide.with(this@AgentsDetailActivity)
                .load(data.role?.displayIcon)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .fitCenter()
                .into(ivAgentRoleIcon)

            Glide.with(this@AgentsDetailActivity)
                .load(data.fullPortrait)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .fitCenter()
                .into(ivAgentPhoto)

            if (data.characterTags.isNullOrEmpty()) {
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
                tagAdapter.submitList(data.characterTags)
            }

            agentSkillAdapter.submitList(data.abilities)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}