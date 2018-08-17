package global.msnthrp.nyheter

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.RelativeLayout
import global.msnthrp.nyheter.extensions.*
import global.msnthrp.nyheter.network.ApiService
import global.msnthrp.nyheter.utils.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var api: ApiService

    private val ivPhoto: ImageView by view(R.id.ivPhoto)
    private val etText: EditText by view(R.id.etText)
    private val btnSend: Button by view(R.id.btnSend)
    private val rlLoader: RelativeLayout by view(R.id.rlLoader)

    private var path: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)
        setContentView(R.layout.activity_main)
        ivPhoto.setOnClickListener { selectPhoto() }
        btnSend.setOnClickListener { send() }
    }

    private fun selectPhoto() {
        if (hasPermissions(this)) {
            chooseFile()
        } else {
            global.msnthrp.nyheter.utils.requestPermissions(this, REQUEST_PERMISSION)
        }
    }

    private fun send(photoId: Int = 0) {
        rlLoader.setVisible(true)
        if (path.isNotEmpty()) {
            uploadPhoto()
            return
        }

        api.post(etText.getAsString(), photoId)
                .subscribeSmart({
                    rlLoader.setVisible(false)
                    etText.clear()
                    ivPhoto.setImageResource(R.mipmap.nyheter)
                    showToast(this, "Successfully sent")
                }, {
                    rlLoader.setVisible(false)
                    showAlert(this, it)
                })
    }

    private fun uploadPhoto() {
        api.upload(toBase64(getBytesFromFile(path)))
                .subscribeSmart({
                    path = ""
                    send(it)
                }, {
                    rlLoader.setVisible(false)
                    showAlert(this, it)
                })
    }

    private fun chooseFile() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_FILE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            REQUEST_FILE -> {
                if (resultCode == Activity.RESULT_OK && data != null) {
                    path = getPath(this, data.data) ?: return
                    ivPhoto.loadUrlForce(this, "file://$path")
                }
            }
        }
    }

    companion object {
        const val REQUEST_PERMISSION = 3301
        const val REQUEST_FILE = 330 + 1
    }
}
